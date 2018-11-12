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

    public UserProfile(Major major, List<FocusArea> focusAreas) {
        this.major = major;
        this.focusAreas = focusAreas;
    }

    public UserProfile(Major major, Set<FocusArea> focusAreas) {
        this.major = major;
        this.focusAreas = new ArrayList<>(focusAreas);
        Collections.sort(this.focusAreas);
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

    @Override
    public boolean equals(Object other) {
        return other == this || ((other instanceof UserProfile)
                && major == ((UserProfile) other).major
                && focusAreas == ((UserProfile) other).focusAreas);
    }
}
