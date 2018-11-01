package seedu.planner.model.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import seedu.planner.model.course.FocusArea;
import seedu.planner.model.course.Major;

//@@author GabrielYik

/**
 * Represents the profile of the user.
 */
public class UserProfile {
    private final Major major;
    private final List<FocusArea> focusAreas;

    public UserProfile() {
        this(Major.COMPUTER_SCIENCE, new ArrayList<>());
    }

    /**
     * Copy constructor
     */
    public UserProfile(UserProfile copy) {
        this.major = copy.major;
        this.focusAreas = new ArrayList<>(copy.focusAreas);
    }

    public UserProfile(String major, Set<String> focusAreas) {
        this.major = mapMajor(major);
        this.focusAreas = mapFocusAreas(focusAreas);
    }

    public UserProfile(Major major, List<FocusArea> focusAreas) {
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
