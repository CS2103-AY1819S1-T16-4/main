package seedu.planner.model.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import seedu.planner.model.course.FocusArea;
import seedu.planner.model.course.Major;
import seedu.planner.model.user.exceptions.UserProfileNotSetUpException;

//@@author GabrielYik

/**
 * Represents the profile of the user.
 */
public class UserProfile {

    private static UserProfile instance;
    private int year;
    private int semester;
    private Major major;
    private List<FocusArea> focusAreas;

    private UserProfile(int year, int semester, Major major, List<FocusArea> focusAreas) {
        this.year = year;
        this.semester = semester;
        this.major = major;
        this.focusAreas = focusAreas;
    }

    /**
     * Gets an instance of the user profile.
     * If the user profile does not exist,
     * {@code UserProfileNotSetUpException} is thrown.
     *
     * @return The user profile instance.
     */
    public static UserProfile getInstance() {
        if (instance == null) {
            throw new UserProfileNotSetUpException();
        }
        return instance;
    }

    /**
     * Sets up the user profile.
     *
     * @param year The current year of study
     * @param semester The current semester in the year of study
     * @param major The major
     * @param focusAreas The focus areas
     */
    public static void setUp(int year, int semester, String major, Set<String> focusAreas) {
        instance = new UserProfile(year, semester, mapMajor(major), mapFocusAreas(focusAreas));
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
     * @return The focus areas as {@code FocusArea}s and sorted
     */
    public static List<FocusArea> mapFocusAreas(Set<String> focusAreas) {
        List<FocusArea> focusAreaList = new ArrayList<FocusArea>();

        for (String fa : focusAreas) {
            focusAreaList.add(mapFocusArea(fa));
        }
        Collections.sort(focusAreaList);

        return focusAreaList;
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
    public List<FocusArea> getFocusAreas() {
        return focusAreas;
    }
}
