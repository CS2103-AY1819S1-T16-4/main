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

WHITELISTS = ["CS", "CEG", "IS", "MA", "GE"]

def filterModule(moduleCode):
  return True
  for w in WHITELISTS:
    if moduleCode.startswith(w):
      return True
  return False

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
count = 0

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

for mod in obj:
  newMod = {}

  moduleCode = mod["ModuleCode"]
  S.add(moduleCode)
  if filterModule(moduleCode):
    newMod["code"] = moduleCode
    newMod["name"] = mod["ModuleTitle"]
    newMod["creditCount"] = float(mod["ModuleCredit"])
    newMod["description"] = mod.get("ModuleDescription", "")
    newMod["preclusions"] = scrapeAllModules(mod.get("Preclusion", ""))
    newMod["prerequisites"] = scrapeAllModules(mod.get("Prerequisite", ""))
    newObj.append(newMod)
    count += 1

for module in newObj:
  prereq = module["prerequisites"]
  for m in prereq:
    if m not in S:
      prereq.remove(m)

  preclu = module["preclusions"]
  for m in preclu:
    if m not in S:
      preclu.remove(m)

print(count)

output = os.path.join(CURRENT_DIR, "..", "src", "main", "resources", "data", "moduleInfo.json")

with open(output, "w", encoding="utf8") as f:
  json.dump(newObj, f, separators=(',',':')) # indent=2

with open(output, "a", encoding="utf8") as f:
  f.write("\n")
