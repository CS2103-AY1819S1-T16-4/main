package seedu.planner.model.course;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

/**
 * Represents the majors of students.
 * Currently, there is only one field present since our target audience
 * is Computer Science students.
 */
public enum Major {
    COMPUTER_SCIENCE("Computer Science"),
    COMPUTER_ENGINEERING("Computer Engineering"),
    @JsonEnumDefaultValue
    UNKNOWN("Unknown");

    private final String name;

    Major(String name) {
        this.name = name;
    }

    /**
     * Check if a string matches a {@code Major} enum value.
     *
     * Note: Case-insensitive
     */
    public static boolean hasMajor(String major) {
        major = major.toLowerCase();
        for (Major m : Major.values()) {
            if (m.toString().toLowerCase().equals(major)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
