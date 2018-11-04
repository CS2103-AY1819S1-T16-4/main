package seedu.planner.model.util;

import java.util.List;

import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;

/**
 * Helper functions for handling module.
 */
public class ModuleUtil {
    public static final String MODULE_CODE_REGEX = "^[A-Z]{2,3}\\d{4}[A-Z]{0,2}$";

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
    private static boolean hasFulfilledAnyPrerequisites(List<Module> modulesTaken, Module moduleToCheck) {
        List<ModuleInfo> prerequisites = moduleToCheck.getPrerequisites();

        if (prerequisites.isEmpty()) {
            return true;
        }

        for (ModuleInfo p: prerequisites) {
            Module m = new Module(p.getCode());

            if (modulesTaken.contains(m)) {
                return true;
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
}
