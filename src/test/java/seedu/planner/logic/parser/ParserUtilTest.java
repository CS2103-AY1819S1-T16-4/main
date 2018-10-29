package seedu.planner.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static seedu.planner.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.planner.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.Module;
import seedu.planner.testutil.Assert;

public class ParserUtilTest {
    // Test data for ModulePlanner
    private static final String INVALID_MODULE_CODE_1 = "CS1@00";
    private static final String INVALID_MODULE_CODE_2 = "CS100#";
    private static final String INVALID_MAJOR = "Computer_Science";
    private static final String INVALID_FOCUS_AREA_1 = "Artificial_Intelligence";
    private static final String INVALID_FOCUS_AREA_2 = "Software-Engineering";
    private static final String INVALID_YEAR = "5";
    private static final String INVALID_SEMESTER = "0";

    private static final String VALID_MODULE_CODE_1 = "CS1000";
    private static final String VALID_MODULE_CODE_2 = "CS2103";
    private static final String VALID_MAJOR = "Computer Science";
    private static final String VALID_FOCUS_AREA_1 = "Artificial Intelligence";
    private static final String VALID_FOCUS_AREA_2 = "Software Engineering";
    private static final String VALID_YEAR = "3";
    private static final String VALID_SEMESTER = "1";

    private static final String WHITESPACE = " \t\r\n";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseIndex_invalidInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIndex("10 a");
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_INVALID_INDEX);
        ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    //@@author Hilda-Ang

    // Test for Module Planner

    @Test
    public void parseModuleCodes_null_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        ParserUtil.parseModuleCodes(null);
    }

    @Test
    public void parseModuleCodes_collectionWithInvalidModuleCodes_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseModuleCodes(Arrays.asList(VALID_MODULE_CODE_1, INVALID_MODULE_CODE_2));
    }

    @Test
    public void parseModuleCodes_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseModuleCodes(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseModuleCodes_collectionWithValidModuleCodes_returnsModuleList() throws Exception {
        List<Module> actualModuleList = ParserUtil.parseModuleCodes(
            Arrays.asList(VALID_MODULE_CODE_1, VALID_MODULE_CODE_2));
        List<Module> expectedModuleList = new ArrayList<>(
            Arrays.asList(new Module(VALID_MODULE_CODE_1), new Module(VALID_MODULE_CODE_2)));

        assertEquals(expectedModuleList, actualModuleList);
    }

    @Test
    public void parseMajor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseMajor((String) null));
    }

    @Test
    public void parseMajor_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseMajor(INVALID_MAJOR));
    }

    @Test
    public void parseMajor_validValue_returnsMajor() throws Exception {
        String expectedMajor = VALID_MAJOR;
        assertEquals(expectedMajor, ParserUtil.parseMajor(VALID_MAJOR));
    }

    @Test
    public void parseFocusAreas_null_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        ParserUtil.parseFocusAreas(null);
    }

    @Test
    public void parseFocusAreas_collectionWithInvalidFocusAreas_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseFocusAreas(Arrays.asList(VALID_FOCUS_AREA_1, INVALID_FOCUS_AREA_2));
    }

    @Test
    public void parseFocusAreas_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseFocusAreas(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseFocusAreas_collectionWithValidFocusAreas_returnsFocusAreaSet() throws Exception {
        Set<String> actualFocusAreaSet = ParserUtil.parseFocusAreas(
            Arrays.asList(VALID_FOCUS_AREA_1, VALID_FOCUS_AREA_2));
        Set<String> expectedFocusAreaSet = new HashSet<>(
            Arrays.asList(VALID_FOCUS_AREA_1, VALID_FOCUS_AREA_2));

        assertEquals(expectedFocusAreaSet, actualFocusAreaSet);
    }

    @Test
    public void parseYear_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseYear((String) null));
    }

    @Test
    public void parseYear_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseYear(INVALID_YEAR));
    }

    @Test
    public void parseYear_validValueWithoutWhitespace_returnsYear() throws Exception {
        int expectedYear = Integer.parseInt(VALID_YEAR);
        assertEquals(expectedYear, ParserUtil.parseYear(VALID_YEAR));
    }

    @Test
    public void parseYear_validValueWithWhitespace_returnsTrimmedYear() throws Exception {
        String yearWithWhitespace = WHITESPACE + VALID_YEAR + WHITESPACE;
        int expectedYear = Integer.parseInt(VALID_YEAR);
        assertEquals(expectedYear, ParserUtil.parseYear(yearWithWhitespace));
    }

    @Test
    public void parseSemester_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseSemester((String) null));
    }

    @Test
    public void parseSemester_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseSemester(INVALID_SEMESTER));
    }

    @Test
    public void parseSemester_validValueWithoutWhitespace_returnsSemester() throws Exception {
        int expectedSemester = Integer.parseInt(VALID_SEMESTER);
        assertEquals(expectedSemester, ParserUtil.parseSemester(VALID_SEMESTER));
    }

    @Test
    public void parseSemester_validValueWithWhitespace_returnsTrimmedSemester() throws Exception {
        String semesterWithWhitespace = WHITESPACE + VALID_SEMESTER + WHITESPACE;
        int expectedSemester = Integer.parseInt(VALID_SEMESTER);
        assertEquals(expectedSemester, ParserUtil.parseYear(semesterWithWhitespace));
    }
}
