package seedu.planner.model.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        assertTrue(isValidSemester(0));
        assertTrue(isValidSemester(3));
    }

    @Test
    public void isValidSemester_validSemester_returnsTrue() {
        assertTrue(isValidSemester(1));
        assertTrue(isValidSemester(2));
    }
}
