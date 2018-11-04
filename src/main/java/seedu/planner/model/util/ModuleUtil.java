package seedu.planner.model.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    //@@author

    //@@author Hilda-Ang

    private static boolean hasNotTakenModule(List<Module> modulesTaken, Module moduleToCheck) {
        return !modulesTaken.contains(moduleToCheck);
    }

    /**
     * Checks if any of the prerequisites for the given {@code Module} have been taken.
     *
     * @param modulesTaken List of {@code Module}s that the user had taken.
     * @param moduleToCheck The {@code Module} to be checked.
     * @return True if all the prerequisites have been taken.
     */
    public static boolean hasFulfilledAllPrerequisites(List<Module> modulesTaken, Module moduleToCheck) {
        List<ModuleInfo> prerequisites = moduleToCheck.getPrerequisites();
        List<List<ModuleInfo>> groupedByEquivalence = groupModuleInfosByEquivalence(prerequisites);

        for (List<ModuleInfo> equivalence : groupedByEquivalence) {
            boolean oneIsNotContained = true;

            for (ModuleInfo moduleInfo : equivalence) {
                Module toModule = new Module(moduleInfo.getCode());

                if (modulesTaken.contains(toModule)) {
                    oneIsNotContained = false;
                }

            }

            if (oneIsNotContained) {
                return false;
            }
        }

        return false;
    }

    /**
     * Checks if none of the preclusions for the given {@code Module} has been taken.
     *
     * @param modulesTaken List of {@code Module}s that the user had taken.
     * @param moduleToCheck The {@code Module} to be checked.
     * @return True if none of the preclusions have been taken.
     */
    private static boolean hasNotFulfilledAnyPreclusions(List<Module> modulesTaken, Module moduleToCheck) {
        List<ModuleInfo> preclusions = moduleToCheck.getPreclusions();

        for (ModuleInfo p: preclusions) {
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
     * @param module moduleToCheck The {@code Module} to be checked.
     * @return True if all the prerequisites are fulfilled and no preclusion has been fulfilled.
     */
    public static boolean isModuleAvailableToTake(List<Module> modulesTaken, List<Module> modulesTakenUntilIndex,
        Module module) {
        return hasNotTakenModule(modulesTaken, module)
            && hasFulfilledAnyPrerequisites(modulesTakenUntilIndex, module)
            && hasNotFulfilledAnyPreclusions(modulesTaken, module);
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
            Iterator<ModuleInfo> iter1 = copyModuleInfoList.iterator();
            List<ModuleInfo> equivalence = new ArrayList<>();

            while (iter1.hasNext()) {
                ModuleInfo current = iter1.next();

                if (equivalence.isEmpty()) {
                    equivalence.add(current);
                    iter1.remove();
                } else {
                    Iterator<ModuleInfo> iter2 = equivalence.iterator();
                    List<ModuleInfo> toAdd = new ArrayList<>();

                    while (iter2.hasNext() && toAdd.size() == 0) {
                        ModuleInfo toCompare = iter2.next();
                        List<ModuleInfo> preclusions1 = current.getPreclusions();
                        List<ModuleInfo> preclusions2 = toCompare.getPreclusions();

                        if (preclusions1.contains(toCompare) || preclusions2.contains(current)) {
                            toAdd.add(current);
                            iter1.remove();
                        }
                    }

                    equivalence.addAll(toAdd);
                }
            }

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
}
