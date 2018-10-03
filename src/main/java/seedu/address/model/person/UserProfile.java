package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.enumeration.FocusArea;
import seedu.address.model.enumeration.Major;


//@@author RomaRomama

/**
 * Presents profile of User
 */
public class UserProfile {

    //Relevant constants
    private static final int MAX_NUM_SEMESTERS = 8;

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
     * Methods returning related information
     */

    public int getYear() { return year; }

    public int getSem() { return sem; }

    public Major getMajor() { return major; }

    public FocusArea getFocusArea() { return focusArea; }

    public int semsToGrad() { return MAX_NUM_SEMESTERS - 2 * year - sem; }

    /**
     * Methods updating related information
     */

    public void setYear(int newYear) { year = newYear; }

    public void setSem(int newSem) { sem = newSem; }

    public void setMajor(Major newMajor) { major = newMajor; }

    public void setFocusArea(FocusArea newFocusArea) { focusArea = newFocusArea; }

}
