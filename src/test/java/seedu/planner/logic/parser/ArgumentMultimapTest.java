package seedu.planner.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.logic.parser.exceptions.ParseException;

public class ArgumentMultimapTest {

    private static ArgumentMultimap argMultimap;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        String commandArg = "preamble c/CS1010 c/CS1231";
        argMultimap = ArgumentTokenizer.tokenize(commandArg, PREFIX_CODE);
        argMultimap.put(PREFIX_CODE, "c/CS2030");
        argMultimap.put(PREFIX_CODE, "c/CS2040 c/CS2103T");
        argMultimap.put(PREFIX_YEAR, "y/1");
        argMultimap.put(PREFIX_SEMESTER, "s/1");
        argMultimap.put(PREFIX_SEMESTER, "s/2");
    }

    @Test
    public void put() {
        assertEquals(4, argMultimap.getAllValues(PREFIX_CODE).size());
        assertEquals(1, argMultimap.getAllValues(PREFIX_YEAR).size());
        assertEquals(0, argMultimap.getAllValues(PREFIX_MAJOR).size());
    }

    @Test
    public void checkPrefixValueCount_prefixPresent() {
        assertEquals(4, argMultimap.checkPrefixValueCount(PREFIX_CODE));
    }

    @Test
    public void checkPrefixValueCount_prefixNotPresent() {
        assertEquals(0, argMultimap.checkPrefixValueCount(PREFIX_MAJOR));
    }

    @Test
    public void containsAllPrefixes_prefixesPresent_returnTrue() throws ParseException {
        assertTrue(argMultimap.containsAllPrefixes(PREFIX_CODE));
        assertTrue(argMultimap.containsAllPrefixes(PREFIX_CODE, PREFIX_YEAR));

    }

    @Test
    public void containsAllPrefixes_prefixesNotPresent_returnFalse() throws ParseException {
        assertFalse(argMultimap.containsAllPrefixes(PREFIX_FOCUS_AREA));
        assertFalse(argMultimap.containsAllPrefixes(PREFIX_FOCUS_AREA, PREFIX_MAJOR));
    }

    @Test
    public void containsAllPrefixes_somePrefixesNotPresent_returnsFalse() throws ParseException {
        assertFalse(argMultimap.containsAllPrefixes(PREFIX_CODE, PREFIX_MAJOR));
    }

    @Test
    public void containsAllPrefixes_prefixNotLimitedToOne_throwParseException() throws ParseException {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Input only one value for the prefix s/");
        argMultimap.containsAllPrefixes(PREFIX_SEMESTER);
    }

    @Test
    public void containsAllPrefixes_somePrefixesNotLimitedToOne_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Input only one value for the prefix s/");
        argMultimap.containsAllPrefixes(PREFIX_SEMESTER, PREFIX_YEAR);
    }
}
