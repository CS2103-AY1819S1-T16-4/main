package seedu.planner.model.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seedu.planner.model.course.ModuleDescription;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;

/**
 * Helper functions for handling module.
 */
public class ModuleUtil {
    private static final String MODULE_CODE_REGEX = "^[A-Z]{2,3}\\d{4}[A-Z]{0,2}$";

    //@@author GabrielYik

    /**
     * Checks if the module code format is valid.
     *
     * @return True if the module code format valid
     */
    public static boolean hasValidCodeFormat(String code) {
        return code.matches(MODULE_CODE_REGEX);
    }

    //@@author Hilda-Ang

    private static boolean hasNotTakenModule(List<Module> modulesTaken, Module moduleToCheck) {
        return !modulesTaken.contains(moduleToCheck);
    }

    /**
     * Checks if any of the prerequisites for the given {@code Module} have been taken.
     *
     * @param modulesTaken  List of {@code Module}s that the user had taken.
     * @param moduleToCheck The {@code Module} to be checked.
     * @return True if all the prerequisites have been taken.
     */
    public static boolean hasFulfilledAllPrerequisites(List<Module> modulesTaken, Module moduleToCheck) {
        List<ModuleInfo> prerequisites = moduleToCheck.getPrerequisites();
        List<List<ModuleInfo>> groupedByEquivalence = groupModuleInfosByEquivalence(prerequisites);

        for (List<ModuleInfo> equivalence : groupedByEquivalence) {
            boolean isOneNotContained = true;

            for (ModuleInfo moduleInfo : equivalence) {
                Module toModule = new Module(moduleInfo.getCode());

                if (modulesTaken.contains(toModule)) {
                    isOneNotContained = false;
                }

            }

            if (isOneNotContained) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if none of the preclusions for the given {@code Module} has been taken.
     *
     * @param modulesTaken  List of {@code Module}s that the user had taken.
     * @param moduleToCheck The {@code Module} to be checked.
     * @return True if none of the preclusions have been taken.
     */
    private static boolean hasNotFulfilledAnyPreclusions(List<Module> modulesTaken, Module moduleToCheck) {
        List<ModuleInfo> preclusions = moduleToCheck.getPreclusions();

        for (ModuleInfo p : preclusions) {
            Module m = new Module(p.getCode());

            if (modulesTaken.contains(m)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the module can be taken by user, i.e all the prerequisites have been taken
     * and none of the preclusions have been taken.
     *
     * @param modulesTaken List of {@code Module}s that the user had taken.
     * @param module       moduleToCheck The {@code Module} to be checked.
     * @return True if all the prerequisites are fulfilled and no preclusion has been fulfilled.
     */
    public static boolean isModuleAvailableToTake(List<Module> modulesTaken, List<Module> modulesTakenBeforeIndex,
                                                  Module module) {
        return hasNotTakenModule(modulesTaken, module)
                && hasFulfilledAllPrerequisites(modulesTakenBeforeIndex, module)
                && hasNotFulfilledAnyPreclusions(modulesTaken, module);
    }

    // @@author rongjiecomputer

    /**
     * Returns true if {@code moduleCode} matches {@code prefix} and moduleCode[len(prefix)] is a digit.
     * <p>REQUIRES:
     * <ul>
     * <li>{@code moduleCode} must starts with alphabets and ends with digits.
     * <li>{@code prefix} must contains alphabets only.
     * </ul>
     */
    private static boolean matchModuleCodePrefix(String moduleCode, String prefix) {
        return moduleCode.startsWith(prefix) && Character.isDigit(moduleCode.charAt(prefix.length()));
    }

    /**
     * Returns true if {@code moduleCode} matches any prefix in {@code prefixes}.
     * <p>REQUIRES:
     * <ul>
     * <li>{@code moduleCode} must starts with alphabets and ends with digits.
     * <li>All prefix in {@code prefixes} must contains alphabets only.
     * </ul>
     */
    public static boolean matchModuleCodePrefixes(String moduleCode, List<String> prefixes) {
        return prefixes.stream().anyMatch(prefix -> matchModuleCodePrefix(moduleCode, prefix));
    }

    /**
     * Give {@code moduleCode} a ranking based on {@code prefixes}.
     * If {@code moduleCode} matches prefix i (starts from 0), returns i,
     * <p>REQUIRES:
     * <ul>
     * <li>{@code moduleCode} must starts with alphabets and ends with digits.
     * <li>All prefix in {@code prefixes} must contains alphabets only.
     * </ul>
     */
    public static int rankModuleCodePrefixes(String moduleCode, List<String> prefixes) {
        int rank = 0;
        for (String prefix : prefixes) {
            if (matchModuleCodePrefix(moduleCode, prefix)) {
                break;
            }
            rank++;
        }
        return rank;
    }

    /**
     * Give {@code moduleCode} a ranking based on its position in {@code priorityList}.
     */
    public static int rankModuleCodeFromPriorityList(String moduleCode, List<ModuleDescription> priorityList) {
        int rank = 0;
        for (ModuleDescription m : priorityList) {
            if (m.getCode().equals(moduleCode)) {
                break;
            }
            rank++;
        }
        return rank;
    }

    //@@author RomaRomama

    /**
     * Extracts the equivalent modules of the head of the list.
     *
     * @param modules list of modules
     * @return List of all modules equivalent to the head
     */
    private static List<ModuleInfo> extractHeadEquivalent(List<ModuleInfo> modules) {
        Iterator<ModuleInfo> iter1 = modules.iterator();
        List<ModuleInfo> equivalence = new ArrayList<>();

        while (iter1.hasNext()) {
            ModuleInfo current = iter1.next();

            if (equivalence.isEmpty()) {
                equivalence.add(current);
            } else {
                List<ModuleInfo> preclusions = equivalence.get(0).getPreclusions();
                if (preclusions.contains(current)) {
                    equivalence.add(current);
                }
            }
        }

        return equivalence;
    }

    /**
     * Finds all equivalent moduleinfos from a given set of moduleinfos.
     *
     * @param moduleInfoList List of {@code ModuleInfo}s.
     * @return The equivalence classes without the single ones.
     */
    private static List<List<ModuleInfo>> findModuleInfoEquivalences(List<ModuleInfo> moduleInfoList) {
        List<ModuleInfo> copyModuleInfoList = new ArrayList<>(moduleInfoList);
        List<List<ModuleInfo>> equivalenceSet = new ArrayList<>();

        while (copyModuleInfoList.size() > 0) {
            List<ModuleInfo> equivalence = extractHeadEquivalent(copyModuleInfoList);
            copyModuleInfoList.removeAll(equivalence);
            if (equivalence.size() > 1) {
                equivalenceSet.add(equivalence);
            }
        }

        return equivalenceSet;
    }

    /**
     * Returns the grouping of equivalent moduleinfos and also include single moduleinfos.
     *
     * @param moduleInfoList List of (@code ModuleInfo)s.
     * @return The equivalence classes including the single ones.
     */
    private static List<List<ModuleInfo>> groupModuleInfosByEquivalence(List<ModuleInfo> moduleInfoList) {
        List<ModuleInfo> copyModuleInfoList = new ArrayList<>(moduleInfoList);
        List<List<ModuleInfo>> allEquivalence = findModuleInfoEquivalences(moduleInfoList);

        for (List<ModuleInfo> equivalence : allEquivalence) {
            copyModuleInfoList.removeAll(equivalence);
        }

        for (ModuleInfo singleModule : copyModuleInfoList) {
            List<ModuleInfo> singleList = new ArrayList<>();
            singleList.add(singleModule);
            allEquivalence.add(singleList);
        }

        return allEquivalence;
    }

    /**
     * A module version for finding the equivalence classes.
     *
     * @param moduleList List of (@code Modules).
     * @return Equivalence classes without the single ones.
     */
    public static List<List<Module>> findModuleEquivalences(List<Module> moduleList) {
        List<ModuleInfo> toModuleInfo = new ArrayList<>();

        for (Module module : moduleList) {
            toModuleInfo.add(module.getInfo());
        }

        List<List<ModuleInfo>> toModuleInfoList = ModuleUtil.findModuleInfoEquivalences(toModuleInfo);
        List<List<Module>> toModuleList = new ArrayList<>();

        for (List<ModuleInfo> moduleInfoList : toModuleInfoList) {
            List<Module> toModule = new ArrayList<>();

            for (ModuleInfo moduleInfo : moduleInfoList) {
                toModule.add(new Module(moduleInfo.getCode()));
            }

            toModuleList.add(toModule);
        }

        return toModuleList;
    }

    //@@author
}
