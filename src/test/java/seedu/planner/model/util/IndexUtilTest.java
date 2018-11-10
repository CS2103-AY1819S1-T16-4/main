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
        assertTrue(isValidIndex(7));;
    }

    @Test
    public void isValidIndex_invalidIndex_returnsFalse() {
        assertFalse(isValidIndex(-1));
        assertFalse(isValidIndex(8));
    }

    @Test
    public void convertYearAndSemesterToIndex_success() {
        assertEquals(convertYearAndSemesterToIndex(1, 1), 0);
        assertEquals(convertYearAndSemesterToIndex(4, 2), 7);
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
    public void getIndicesFromYear_success() {
        assertEquals(getIndicesFromYear(1)[0], new int[] {0, 1}[0]);
        assertEquals(getIndicesFromYear(2)[0], new int[] {2, 3}[0]);
        assertEquals(getIndicesFromYear(3)[1], new int[] {4, 5}[1]);
        assertEquals(getIndicesFromYear(4)[1], new int[] {6, 7}[1]);
    }
}
