package seedu.planner.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.model.util.IndexUtil.convertYearAndSemesterToIndex;
import static seedu.planner.model.util.IndexUtil.isValidIndex;
import static seedu.planner.model.util.IndexUtil.isValidSemester;
import static seedu.planner.model.util.IndexUtil.isValidYear;

import org.junit.Test;

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
}
