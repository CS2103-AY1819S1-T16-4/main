package seedu.planner.model.tab;

/**
 * A class to hold information about a ui tab.
 */
public class Tab {

    public static final String TAB_NAME_REGEX = "^[A-Z]\\d[A-Z]\\d$";

    public static final String MESSAGE_TAB_NAME_CONSTRAINTS = "Tab names should be of the format yNsM, "
            + "where N refers to the year of study, which is a number between 1 and 4, "
            + "and M refers to the semester in a year, which is a number between 1 and 2";

    private Tab() {

    }
}
