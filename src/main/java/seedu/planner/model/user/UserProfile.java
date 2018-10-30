package seedu.planner.model.user;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import seedu.planner.model.course.FocusArea;
import seedu.planner.model.course.Major;
import seedu.planner.model.user.exceptions.UserProfileNotSetUpException;

//@@author GabrielYik

/**
 * Represents the profile of the user.
 * This class is a singleton.
 */
public class UserProfile {
    private int year;
    private int semester;
    private Major major;
    private Set<FocusArea> focusAreas;

    public UserProfile() {
        this(1, 1, Major.COMPUTER_SCIENCE, EnumSet.of(FocusArea.SOFTWARE_ENGINEERING));
    }

    /**
     * Copy constructor
     */
    public UserProfile(UserProfile copy) {
        this.year = copy.year;
        this.semester = copy.semester;
        this.major = copy.major;
        this.focusAreas = EnumSet.copyOf(copy.focusAreas);
    }

    public UserProfile(int year, int semester, String major, Set<String> focusAreas) {
        this.year = year;
        this.semester = semester;
        this.major = mapMajor(major);
        this.focusAreas = mapFocusAreas(focusAreas);
    }

    public UserProfile(int year, int semester, Major major, Set<FocusArea> focusAreas) {
        this.year = year;
        this.semester = semester;
        this.major = major;
        this.focusAreas = focusAreas;
    }

    /**
     * Converts the major from a {@code String} to a {@code Major}.
     *
     * @param major The major as a {@code String}
     * @return The major as a {@code Major}
     */
    public static Major mapMajor(String major) {
        for (Major m : Major.values()) {
            if (m.toString().equals(major)) {
                return m;
            }
        }
        return Major.UNKNOWN;
    }

    /**
     * Converts the focus area from a {@code String} to a {@code FocusArea}.
     *
     * @param focusArea The focus area as a {@code String}
     * @return The focus area as a {@code FocusArea}
     */
    private static FocusArea mapFocusArea(String focusArea) {
        for (FocusArea fa : FocusArea.values()) {
            if (fa.toString().equals(focusArea)) {
                return fa;
            }
        }
        return FocusArea.UNKNOWN;
    }

    /**
     * Converts the focus areas from {@code String}s to {@code FocusArea}s.
     * Each focus area is converted from a {@code String} to a {@code FocusArea}
     * using the method {@link #mapFocusArea(String) mapFocusArea}
     *
     * @param focusAreas The focus areas as {@code String}s
     * @return The focus areas as {@code FocusArea}s
     */
    public static Set<FocusArea> mapFocusAreas(Set<String> focusAreas) {
        Set<FocusArea> focusAreasSet = new HashSet<>();
        for (String fa : focusAreas) {
            focusAreasSet.add(mapFocusArea(fa));
        }
        return focusAreasSet;
    }

    /**
     * Returns the current year of study.
     *
     * @return The current year of study
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the current semester in the year of study.
     *
     * @return The current semester in the year of study
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Returns the major.
     *
     * @return The major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Returns the focus areas.
     *
     * @return The focus areas
     */
    public Set<FocusArea> getFocusAreas() {
        return focusAreas;
    }
}
