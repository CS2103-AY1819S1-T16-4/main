package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_SEMESTER_DESC_THREE;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_YEAR_DESC_FIVE;
import static seedu.planner.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.planner.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ZERO;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.planner.logic.commands.SuggestCommand;

public class SuggestCommandParserTest {
    private SuggestCommandParser parser = new SuggestCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE,
            new SuggestCommand(VALID_INDEX_ZERO));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE);

        // missing year prefix
        assertParseFailure(parser, VALID_YEAR_ONE + VALID_SEMESTER_DESC_ONE, expectedMessage);

        // missing semester prefix
        assertParseFailure(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_ONE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_YEAR_ONE + " " + VALID_SEMESTER_ONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid year
        assertParseFailure(parser, INVALID_YEAR_DESC_FIVE + VALID_SEMESTER_DESC_ONE,
            ParserUtil.MESSAGE_INVALID_YEAR);

        // invalid semester
        assertParseFailure(parser, VALID_YEAR_DESC_ONE + INVALID_SEMESTER_DESC_THREE,
            ParserUtil.MESSAGE_INVALID_SEMESTER);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_YEAR_DESC_FIVE + INVALID_SEMESTER_DESC_THREE,
            ParserUtil.MESSAGE_INVALID_YEAR);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }
}
