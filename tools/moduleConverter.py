#! python3
#
# This script will download the module information JSON file from NUSMods,
# then sanitize it for use of Module Planner app.
#

import json
import os
import re
import urllib.request, urllib.error

CURRENT_DIR = os.path.abspath(os.path.dirname(__file__))
NUSMODS_JSON = os.path.join(CURRENT_DIR, "nusmods.json")
OUTPUT_JSON = os.path.join(CURRENT_DIR, "..", "src", "main", "resources", "data", "moduleInfo.json")

def notNull(str):
  return str.lower() != "nil"

if not os.path.exists(NUSMODS_JSON):
  req = urllib.request.Request("https://api.nusmods.com/2018-2019/moduleInformation.json")
  res = urllib.request.urlopen(req)
  data = res.read()
  with open(NUSMODS_JSON, "w", encoding="utf8") as f:
    f.write(data.decode("utf8"))

with open(NUSMODS_JSON, "r", encoding="utf8") as f:
  obj = json.load(f)

newObj = []

modulePattern = re.compile(r"([A-Z]{2,3})(\d{4})([A-Z]{0,2})")

def scrapeAllModules(str):
  x = []

  if str:
    match = modulePattern.search(str)
    while match:
      x.append(match.group(0))
      match = modulePattern.search(str, match.end())

  return x

S = set()
Map = {}

for mod in obj:
  newMod = {}

  moduleCode = mod["ModuleCode"]
  S.add(moduleCode)
  newMod["code"] = moduleCode
  newMod["name"] = mod["ModuleTitle"]
  newMod["creditCount"] = float(mod["ModuleCredit"])
  newMod["description"] = mod.get("ModuleDescription", "")
  newMod["preclusions"] = scrapeAllModules(mod.get("Preclusion", ""))
  newMod["prerequisites"] = scrapeAllModules(mod.get("Prerequisite", ""))
  newObj.append(newMod)

  Map[moduleCode] = newMod

# Remove module codes that do not have its module info entry
def deleteModules(newObj):
  res = 0
  for module in newObj:
    prereq = module["prerequisites"]
    for m in prereq:
      if m not in S:
        res += 1
        prereq.remove(m)

    preclu = module["preclusions"]
    for m in preclu:
      if m not in S:
        res += 1
        preclu.remove(m)

  return res

while deleteModules(newObj) > 0:
  pass

# Ensure backward preclusion relationship
for module in newObj:
  preclu = module["preclusions"]
  for moduleCode in preclu:
    m = Map[moduleCode]
    if module["code"] not in m["preclusions"]:
      m["preclusions"].append(module["code"])

# Remove MA1301 and MA1301X as prereq
A_LEVEL_MATHS = ["MA1301", "MA1301X"]
for module in newObj:
  for ma in ["MA1301", "MA1301X"]:
    if ma in module["prerequisites"] and module["code"] not in A_LEVEL_MATHS:
      module["prerequisites"].remove(ma)

print(len(newObj))

with open(OUTPUT_JSON, "w", encoding="utf8") as f:
  json.dump(newObj, f, separators=(',',':')) # indent=2

with open(OUTPUT_JSON, "a", encoding="utf8") as f:
  f.write("\n")
