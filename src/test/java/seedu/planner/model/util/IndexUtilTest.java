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
import static seedu.planner.testutil.TypicalIndexes.INDEX_EIGHT;
import static seedu.planner.testutil.TypicalIndexes.INDEX_FIVE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_FOUR;
import static seedu.planner.testutil.TypicalIndexes.INDEX_MINUS_ONE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ONE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_SEVEN;
import static seedu.planner.testutil.TypicalIndexes.INDEX_SIX;
import static seedu.planner.testutil.TypicalIndexes.INDEX_THREE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_TWO;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ZERO;

import org.junit.Test;

import seedu.planner.commons.util.Pair;

public class IndexUtilTest {

    @Test
    public void isValidYear_validYear_returnsTrue() {
        assertTrue(isValidYear(INDEX_ONE));
        assertTrue(isValidYear(INDEX_TWO));
        assertTrue(isValidYear(INDEX_THREE));
        assertTrue(isValidYear(INDEX_FOUR));
    }

    @Test
    public void isValidYear_invalidYear_returnsFalse() {
        assertFalse(isValidYear(INDEX_ZERO));
        assertFalse(isValidYear(INDEX_FIVE));
    }

    @Test
    public void isValidSemester_validSemester_returnsTrue() {
        assertTrue(isValidSemester(INDEX_ONE));
        assertTrue(isValidSemester(INDEX_TWO));
    }

    @Test
    public void isValidSemester_invalidSemester_returnsFalse() {
        assertFalse(isValidSemester(INDEX_ZERO));
        assertFalse(isValidSemester(INDEX_THREE));
    }

    @Test
    public void isValidIndex_validIndex_returnsTrue() {
        assertTrue(isValidIndex(INDEX_ZERO));
        assertTrue(isValidIndex(INDEX_ONE));
        assertTrue(isValidIndex(INDEX_TWO));
        assertTrue(isValidIndex(INDEX_THREE));
        assertTrue(isValidIndex(INDEX_FOUR));
        assertTrue(isValidIndex(INDEX_FIVE));
        assertTrue(isValidIndex(INDEX_SIX));
        assertTrue(isValidIndex(INDEX_SEVEN));
    }

    @Test
    public void isValidIndex_invalidIndex_returnsFalse() {
        assertFalse(isValidIndex(INDEX_MINUS_ONE));
        assertFalse(isValidIndex(INDEX_EIGHT));
    }

    @Test
    public void convertYearAndSemesterToIndex_success() {
        assertEquals(convertYearAndSemesterToIndex(INDEX_ONE, INDEX_ONE), INDEX_ZERO);
        assertEquals(convertYearAndSemesterToIndex(INDEX_ONE, INDEX_TWO), INDEX_ONE);
        assertEquals(convertYearAndSemesterToIndex(INDEX_TWO, INDEX_ONE), INDEX_TWO);
        assertEquals(convertYearAndSemesterToIndex(INDEX_TWO, INDEX_TWO), INDEX_THREE);
        assertEquals(convertYearAndSemesterToIndex(INDEX_THREE, INDEX_ONE), INDEX_FOUR);
        assertEquals(convertYearAndSemesterToIndex(INDEX_THREE, INDEX_TWO), INDEX_FIVE);
        assertEquals(convertYearAndSemesterToIndex(INDEX_FOUR, INDEX_ONE), INDEX_SIX);
        assertEquals(convertYearAndSemesterToIndex(INDEX_FOUR, INDEX_TWO), INDEX_SEVEN);
    }

    @Test
    public void convertIndexToYearAndSemester_validIndex_success() {
        assertEquals(convertIndexToYearAndSemester(INDEX_ZERO), new Pair(INDEX_ONE, INDEX_ONE));
        assertEquals(convertIndexToYearAndSemester(INDEX_ONE), new Pair(INDEX_ONE, INDEX_TWO));
        assertEquals(convertIndexToYearAndSemester(INDEX_TWO), new Pair(INDEX_TWO, INDEX_ONE));
        assertEquals(convertIndexToYearAndSemester(INDEX_THREE), new Pair(INDEX_TWO, INDEX_TWO));
        assertEquals(convertIndexToYearAndSemester(INDEX_FOUR), new Pair(INDEX_THREE, INDEX_ONE));
        assertEquals(convertIndexToYearAndSemester(INDEX_FIVE), new Pair(INDEX_THREE, INDEX_TWO));
        assertEquals(convertIndexToYearAndSemester(INDEX_SIX), new Pair(INDEX_FOUR, INDEX_ONE));
        assertEquals(convertIndexToYearAndSemester(INDEX_SEVEN), new Pair(INDEX_FOUR, INDEX_TWO));
    }

    @Test
    public void convertIndexToYearAndSemester_invalidIndex_failure() {
        assertEquals(convertIndexToYearAndSemester(INDEX_MINUS_ONE), new Pair(INDEX_ZERO, INDEX_ZERO));
        assertEquals(convertIndexToYearAndSemester(INDEX_EIGHT), new Pair(INDEX_ZERO, INDEX_ZERO));
    }

    @Test
    public void getIndicesFromYear_success() {
        assertEquals(getIndicesFromYear(INDEX_ONE)[INDEX_ZERO], INDEX_ZERO);
        assertEquals(getIndicesFromYear(INDEX_ONE)[INDEX_ONE], INDEX_ONE);
        assertEquals(getIndicesFromYear(INDEX_TWO)[INDEX_ZERO], INDEX_TWO);
        assertEquals(getIndicesFromYear(INDEX_TWO)[INDEX_ONE], INDEX_THREE);
        assertEquals(getIndicesFromYear(INDEX_THREE)[INDEX_ZERO], INDEX_FOUR);
        assertEquals(getIndicesFromYear(INDEX_THREE)[INDEX_ONE], INDEX_FIVE);
        assertEquals(getIndicesFromYear(INDEX_FOUR)[INDEX_ZERO], INDEX_SIX);
        assertEquals(getIndicesFromYear(INDEX_FOUR)[INDEX_ONE], INDEX_SEVEN);
    }
}
