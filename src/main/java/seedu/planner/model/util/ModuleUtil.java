package seedu.planner.model.util;

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
}
