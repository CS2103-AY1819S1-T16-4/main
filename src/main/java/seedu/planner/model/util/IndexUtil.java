package seedu.planner.model.util;

//@@author Hilda-Ang

/**
 * Contains utility methods for handling year, semester, and indexing.
 */
public class IndexUtil {
    public static final int NUM_OF_SEMESTER_IN_YEAR = 2;

    /**
     * Checks that the given year is valid, i.e. is between 1 to 4.
     *
     * @param year Year to be checked.
     * @return True if the given year is valid.
     */
    public static boolean hasValidYear(int year) {
        return year > 0 && year < 5;
    }

    /**
     * Checks that the given semester is valid, i.e. is either 1 or 2.
     *
     * @param semester Semester to be checked.
     * @return True if the given semester is valid.
     */
    public static boolean hasValidSemester(int semester) {
        return semester == 1 || semester == 2;
    }

    /**
     * Converts the given year and semester to an index between 1 to 8,
     * e.g. year 1 semester 1 will be converted to index 1,
     * year 1 semester 2 will be converted to index 2, and so on.
     *
     * @param year An integer between 1 to 4.
     * @param semester An integer between 1 to 2.
     * @return The resulting index between 1 to 8.
     */
    public static int convertYearAndSemesterToIndex(int year, int semester) {
        return year * NUM_OF_SEMESTER_IN_YEAR - NUM_OF_SEMESTER_IN_YEAR + semester;
    }
}
