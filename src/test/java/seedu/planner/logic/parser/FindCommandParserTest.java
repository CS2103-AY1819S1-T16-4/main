package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.commands.FindCommand;

//@@author GabrielYik

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_oneValidArg_success() {
        FindCommand command = new FindCommand(VALID_MODULE_CS1010);

        assertParseSuccess(parser, VALID_MODULE_CODE_DESC_CS1010, command);
    }

    @Test
    public void parse_oneInvalidArg_failure() {
        String userInput = " " + PREFIX_FOCUS_AREA + "Sleep science";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_twoValidArgs_throwsParseException() {
        String userInput = " " + VALID_MODULE_CODE_DESC_CS1010
                + VALID_MODULE_CODE_DESC_CS1231;

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(Messages.MESSAGE_EXTRA_PREFIX_VALUES, PREFIX_CODE));

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
