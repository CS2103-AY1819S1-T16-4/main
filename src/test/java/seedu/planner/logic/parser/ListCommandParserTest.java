package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_YEAR_DESC_FIVE;
import static seedu.planner.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.planner.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.planner.model.util.IndexUtil.VALUE_NOT_AVAILABLE;

import org.junit.Test;

import seedu.planner.logic.commands.ListCommand;

//@@author Hilda-Ang

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_noFieldPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE, new ListCommand(VALUE_NOT_AVAILABLE));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_YEAR_DESC_ONE, new ListCommand(VALID_YEAR_ONE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid year
        assertParseFailure(parser, INVALID_YEAR_DESC_FIVE, ParserUtil.MESSAGE_INVALID_YEAR);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_YEAR_DESC_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
