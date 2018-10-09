#! python3
#
# This script will download the module information JSON file from NUSMods,
# then sanitize it for use of Module Planner app.
#

import json
import os
import urllib.request, urllib.error

CURRENT_DIR = os.path.abspath(os.path.dirname(__file__))
NUSMODS_JSON = os.path.join(CURRENT_DIR, "nusmods.json")

WHITELISTS = ["CS", "CEG", "IS", "MA", "GE"]

def filterModule(moduleCode):
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

for mod in obj:
  newMod = {}

  moduleCode = mod["ModuleCode"]
  if filterModule(moduleCode):
    newMod["code"] = moduleCode
    newMod["name"] = mod["ModuleTitle"]
    newMod["creditCount"] = int(mod["ModuleCredit"])
    if "Preclusion" in mod:
      if notNull(mod["Preclusion"]):
        newMod["Preclusion"] = mod["Preclusion"]
    if "Prerequisite" in mod:
      if notNull(mod["Prerequisite"]):
        newMod["Prerequisite"] = mod["Prerequisite"]
    newObj.append(newMod)
    count += 1

print(count)

with open(os.path.join(CURRENT_DIR, "sanitized-moduleInfo.json"), "w", encoding="utf8") as f:
  json.dump(newObj, f, indent=2)
