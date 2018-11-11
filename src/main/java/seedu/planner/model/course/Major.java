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
     * Converts the major from a {@code String} to a {@code Major}.
     *
     * Note: Case-insensitive
     *
     * @param major The major as a {@code String}
     * @return The major as a {@code Major}
     */
    public static Major mapMajor(String major) {
        for (Major m : Major.values()) {
            if (m.toString().equalsIgnoreCase(major)) {
                return m;
            }
        }
        return Major.UNKNOWN;
    }

    @Override
    public String toString() {
        return name;
    }
}
