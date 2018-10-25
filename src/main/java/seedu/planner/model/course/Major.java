package seedu.planner.model.course;

//@@author GabrielYik

/**
 * Represents the majors of students.
 * Currently, there is only one field present since our target audience
 * is Computer Science students.
 */
public enum Major {
    COMPUTER_SCIENCE("Computer science"),
    COMPUTER_ENGINEERING("Computer engineering"),
    UNKNOWN("Unknown");

    private final String name;

    Major(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
