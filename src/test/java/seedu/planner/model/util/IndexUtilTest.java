package seedu.planner.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.model.util.IndexUtil.convertIndexToYearAndSemester;
import static seedu.planner.model.util.IndexUtil.convertYearAndSemesterToIndex;
import static seedu.planner.model.util.IndexUtil.getIndicesFromYear;
import static seedu.planner.model.util.IndexUtil.isValidIndex;
import static seedu.planner.model.util.IndexUtil.isValidSemester;
import static seedu.planner.model.util.IndexUtil.isValidYear;

import org.junit.Test;

import seedu.planner.commons.util.Pair;

public class IndexUtilTest {

    @Test
    public void isValidYear_validYear_returnsTrue() {
        assertTrue(isValidYear(1));
        assertTrue(isValidYear(2));
        assertTrue(isValidYear(3));
        assertTrue(isValidYear(4));
    }

    @Test
    public void isValidYear_invalidYear_returnsFalse() {
        assertFalse(isValidYear(0));
        assertFalse(isValidYear(5));
    }

    @Test
    public void isValidSemester_validSemester_returnsTrue() {
        assertTrue(isValidSemester(1));
        assertTrue(isValidSemester(2));
    }

    @Test
    public void isValidSemester_invalidSemester_returnsFalse() {
        assertFalse(isValidSemester(0));
        assertFalse(isValidSemester(3));
    }

    @Test
    public void isValidIndex_validIndex_returnsTrue() {
        assertTrue(isValidIndex(0));
        assertTrue(isValidIndex(1));
        assertTrue(isValidIndex(2));
        assertTrue(isValidIndex(3));
        assertTrue(isValidIndex(4));
        assertTrue(isValidIndex(5));
        assertTrue(isValidIndex(6));
        assertTrue(isValidIndex(7));;
    }

    @Test
    public void isValidIndex_invalidIndex_returnsFalse() {
        assertFalse(isValidIndex(-1));
        assertFalse(isValidIndex(8));
    }

    @Test
    public void convertYearAndSemesterToIndex_validValues_success() {
        assertEquals(convertYearAndSemesterToIndex(1, 1), 0);
        assertEquals(convertYearAndSemesterToIndex(1, 2), 1);
        assertEquals(convertYearAndSemesterToIndex(2, 1), 2);
        assertEquals(convertYearAndSemesterToIndex(2, 2), 3);
        assertEquals(convertYearAndSemesterToIndex(3, 1), 4);
        assertEquals(convertYearAndSemesterToIndex(3, 2), 5);
        assertEquals(convertYearAndSemesterToIndex(4, 1), 6);
        assertEquals(convertYearAndSemesterToIndex(4, 2), 7);
    }

    @Test
    public void convertYearAndSemesterToIndex_invalidValues_failure() {
        // invalid years
        assertEquals(convertYearAndSemesterToIndex(0, 1), -1);
        assertEquals(convertYearAndSemesterToIndex(5, 1), -1);

        //invalid semesters
        assertEquals(convertYearAndSemesterToIndex(1, 0), -1);
        assertEquals(convertYearAndSemesterToIndex(1, 3), -1);
    }

    @Test
    public void convertIndexToYearAndSemester_validIndex_success() {
        assertEquals(convertIndexToYearAndSemester(0), new Pair(1, 1));
        assertEquals(convertIndexToYearAndSemester(1), new Pair(1, 2));
        assertEquals(convertIndexToYearAndSemester(2), new Pair(2, 1));
        assertEquals(convertIndexToYearAndSemester(4), new Pair(3, 1));
        assertEquals(convertIndexToYearAndSemester(6), new Pair(4, 1));
    }

    @Test
    public void convertIndexToYearAndSemester_invalidIndex_failure() {
        assertEquals(convertIndexToYearAndSemester(-1), new Pair(0, 0));
        assertEquals(convertIndexToYearAndSemester(8), new Pair(0, 0));
    }

    @Test
    public void getIndicesFromYear_validYear_success() {
        assertEquals(getIndicesFromYear(1)[0], 0);
        assertEquals(getIndicesFromYear(1)[1], 1);
        assertEquals(getIndicesFromYear(2)[0], 2);
        assertEquals(getIndicesFromYear(2)[1], 3);
        assertEquals(getIndicesFromYear(3)[0], 4);
        assertEquals(getIndicesFromYear(3)[1], 5);
        assertEquals(getIndicesFromYear(4)[0], 6);
        assertEquals(getIndicesFromYear(4)[1], 7);
    }

    @Test
    public void getIndicesFromYear_invalidYear_failure() {
        assertEquals(getIndicesFromYear(0)[0], -1);
        assertEquals(getIndicesFromYear(0)[1], -1);
        assertEquals(getIndicesFromYear(5)[0], -1);
        assertEquals(getIndicesFromYear(5)[1], -1);
    }
}
