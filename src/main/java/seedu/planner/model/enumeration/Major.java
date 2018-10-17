package seedu.planner.model.enumeration;

//@@author GabrielYik

/**
 * Represents the majors of students.
 * Currently, there is only one field present since our target audience
 * is Computer Science students.
 */
public enum Major {
    COMPUTER_SCIENCE("Computer Science"),
    COMPUTER_ENGINEERING("Computer Engineering");

    private final String name;

    Major(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
