package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.MODULE_DESC_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.planner.logic.commands.AddCommand;

//@@author RomaRomama

/**
 * Tests for AddCommandParser
 */
public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        //missing year prefix
        assertParseFailure(parser, VALID_YEAR_ONE + VALID_SEMESTER_DESC_ONE + MODULE_DESC_CS1010,
                expectedMessage);

        //missing semester prefix
        assertParseFailure(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_ONE + MODULE_DESC_CS1010,
                expectedMessage);

        //missing code prefix
        assertParseFailure(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE + VALID_MODULE_CS1010,
                expectedMessage);
    }
}
