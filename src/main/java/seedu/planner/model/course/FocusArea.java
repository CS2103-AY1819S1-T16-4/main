package seedu.planner.model.course;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

/**
 * Represents the focus areas of computer science students.
 * These focus areas are applicable only to new cohorts from AY2015-16 onwards.
 */
public enum FocusArea {
    ALGORITHMS_AND_THEORY("Algorithms and Theory"),
    ARTIFICIAL_INTELLIGENCE("Artificial Intelligence"),
    COMPUTER_GRAPHICS_AND_GAMES("Computer Graphics and Games"),
    COMPUTER_SECURITY("Computer Security"),
    DATABASE_SYSTEMS("Database Systems"),
    MULTIMEDIA_INFORMATION_RETRIEVAL("Multimedia Information Retrieval"),
    NETWORKING_AND_DISTRIBUTED_SYSTEMS("Networking and Distributed Systems"),
    PARALLEL_COMPUTING("Parallel Computing"),
    PROGRAMMING_LANGUAGES("Programming Languages"),
    SOFTWARE_ENGINEERING("Software Engineering"),
    @JsonEnumDefaultValue
    UNKNOWN("Unknown");

    private final String name;

    FocusArea(String name) {
        this.name = name;
    }

    /**
     * Check if a string matches a {@code FocusArea} enum value.
     *
     * Note: Case-insensitive
     */
    private static boolean hasFocusArea(String focusArea) {
        focusArea = focusArea.toLowerCase();
        for (FocusArea fa : FocusArea.values()) {
            if (fa.toString().toLowerCase().equals(focusArea)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a set of strings all match the enum values of {@code FocusArea}.
     *
     * Note: Case-insensitive
     */
    public static boolean hasFocusAreas(Set<String> focusArea) {
        for (String fa : focusArea) {
            if (!hasFocusArea(fa)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
