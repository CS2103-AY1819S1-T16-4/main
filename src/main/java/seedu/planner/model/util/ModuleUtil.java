package seedu.planner.model.util;

import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;

import java.util.List;

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
    private static boolean hasFulfilledAllPrerequisites(List<Module> modulesTaken, Module moduleToCheck) {
        List<ModuleInfo> prerequisites = moduleToCheck.getPrerequisites();

        for (ModuleInfo p: prerequisites) {
            Module m = new Module(p.getCode());

            if (!modulesTaken.contains(m)) {
                return false;
            }
        }

        return true;
    }

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

    public static boolean isModuleAvailableToTake(List<Module> modulesTaken, Module module) {
        return hasFulfilledAllPrerequisites(modulesTaken, module) && hasNotFulfilledAnyPreclusions(modulesTaken, module);
    }
}
