package seedu.planner.model.util;

/**
 * Helper functions for handling module.
 */
public class ModuleUtil {
    public static final String moduleCodeRegex = "^[A-Z]{2,3}[0-9]{4}[A-Z]{0,1}$";

    //@@author GabrielYik

    /**
     * Checks if the module code format is valid.
     *
     * @return True if the module code format valid
     */
    public static boolean hasValidCodeFormat(String code) {
        return code.matches(moduleCodeRegex);
    }

    //@@author
}
