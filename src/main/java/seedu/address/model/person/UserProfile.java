package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.enumeration.*;
import java.util.Collections;
import java.util.Objects;


//@@author RomaRomama

/**
 * Presents profile of User
 */

public class UserProfile {

    //Identity fields
    private int year;
    private int sem;
    private Major major;
    private FocusArea focusArea;

    /**
     * Every field must be present and not null
     */
    public UserProfile(int year, int sem, Major major, FocusArea focusArea) {
        requireAllNonNull(year, sem, major, focusArea);
        this.year = year;
        this.sem = sem;
        this.major = major;
        this.focusArea = focusArea;
    }

    /**
     *
     * @return related infos
     */
    public int getYear() { return year; }

    public int getSem() { return sem; }

    public Major getMajor() { return major; }

    public FocusArea getFocusArea() { return focusArea; }

    public int SemsToGrad() { return 8 - 2 * year - sem; }

    /**
     * update infos
     */
    public void setYear(int new_year) { year = new_year; }

    public void setSem(int new_sem) { sem = new_sem; }

    public void setMajor(Major new_major) { major = new_major; }

    public void setFocusArea(FocusArea new_focusArea) { focusArea = new_focusArea; }

}
