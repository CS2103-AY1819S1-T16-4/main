package seedu.planner.model.course;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

/**
 * Represents the focus areas of computer science students.
 * These focus areas are applicable only to new cohorts from AY2015-16 onwards.
 */
public enum FocusArea {
    ALGORITHMS_AND_THEORY("Algorithms and Theory"),
    ARTIFICIAL_INTELLIGENCE("Artificial Intelligence"),
    // COMPUTER_GRAPHICS_AND_GAMES("Computer Graphics and Games"),
    // COMPUTER_SECURITY("Computer Security"),
    // DATABASE_SYSTEMS("Database Systems"),
    // MULTIMEDIA_INFORMATION_RETRIEVAL("Multimedia Information Retrieval"),
    // NETWORKING_AND_DISTRIBUTED_SYSTEMS("Networking and Distributed Systems"),
    // PARALLEL_COMPUTING("Parallel Computing"),
    // PROGRAMMING_LANGUAGES("Programming Languages"),
    SOFTWARE_ENGINEERING("Software Engineering"),
    @JsonEnumDefaultValue
    UNKNOWN("Unknown");

    private final String name;

    FocusArea(String name) {
        this.name = name;
    }

    /**
     * Converts the focus area from a {@code String} to a {@code FocusArea}.
     * <p>
     * Note: Case-insensitive
     *
     * @param focusArea The focus area as a {@code String}
     * @return The focus area as a {@code FocusArea}
     */
    private static FocusArea mapFocusArea(String focusArea) {
        for (FocusArea fa : FocusArea.values()) {
            if (fa.toString().equalsIgnoreCase(focusArea)) {
                return fa;
            }
        }
        return FocusArea.UNKNOWN;
    }

    /**
     * Converts a set of strings to a set of matching {@code FocusArea}, skip those that are invalid.
     * <p>
     * Note: Case-insensitive
     */
    public static Set<FocusArea> filterFocusAreas(Set<String> focusArea) {
        Set<FocusArea> result = new HashSet<>();
        for (String fa : focusArea) {
            FocusArea mappedFocusArea = mapFocusArea(fa);
            if (mappedFocusArea != UNKNOWN) {
                result.add(mappedFocusArea);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
