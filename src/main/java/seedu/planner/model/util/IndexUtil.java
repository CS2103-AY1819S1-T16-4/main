package seedu.planner.model.util;

//@@author Hilda-Ang

/**
 * Contains utility methods for handling year, semester, and indexing.
 */
public class IndexUtil {
    public static final int VALUE_NOT_AVAILABLE = -1;

    public static final int NUM_OF_SEMESTER_IN_YEAR = 2;

    /**
     * Checks that the given year is valid, i.e. is between 1 to 4.
     *
     * @param year Year to be checked.
     * @return True if the given year is valid.
     */
    public static boolean isValidYear(int year) {
        return year > 0 && year < 5;
    }

    /**
     * Checks that the given semester is valid, i.e. is either 1 or 2.
     *
     * @param semester Semester to be checked.
     * @return True if the given semester is valid.
     */
    public static boolean isValidSemester(int semester) {
        return semester == 1 || semester == 2;
    }

    /**
     * Checks that the given index is valid, i.e. is between 0 and 7 inclusive.
     *
     * @param index Index to be checked.
     * @return True if the given index is valid.
     */
    public static boolean isValidIndex(int index) {
        return index >= 0 && index <= 7;
    }

    /**
     * Converts the given year and semester to an index between 0 and 7,
     * e.g. year 1 semester 1 will be converted to index 0,
     * year 1 semester 2 will be converted to index 1,
     * and year 2 semester 1 will be converted to index 2.
     *
     * @param year An integer between 1 to 4.
     * @param semester An integer between 1 to 2.
     * @return The resulting index between 0 to 7.
     */
    public static int convertYearAndSemesterToIndex(int year, int semester) {
        return year * NUM_OF_SEMESTER_IN_YEAR - NUM_OF_SEMESTER_IN_YEAR + semester - 1;
    }

    /**
     * Converts an index to the corresponding year and semester.
     * If the index is not valid (not between 0 and 7), "00" is
     * returned.
     *
     * @param index The index
     * @return The corresponding year and semester concatenated as a string
     */
    public static String convertIndexToYearAndSemester(int index) {
        if (!isValidIndex(index)) {
            return "00";
        }

        int year = 1;
        int semester = 1;
        while (index >= 2) {
            index -= 2;
            year++;
        }
        semester += index;

        return String.valueOf(year) + String.valueOf(semester);
    }

    public static int[] getIndicesFromYear(int year) {
        int[] indices = new int[2];

        final int indexZero = 0;
        final int indexOne = 1;

        if (year == 1) {
            indices[indexZero] = 0;
            indices[indexOne] = 1;
        } else if (year == 2) {
            indices[indexZero] = 2;
            indices[indexOne] = 3;
        } else if (year == 3) {
            indices[indexZero] = 4;
            indices[indexOne] = 5;
        } else if (year == 4) {
            indices[indexZero] = 6;
            indices[indexOne] = 7;
        }

        return indices;
    }
}
