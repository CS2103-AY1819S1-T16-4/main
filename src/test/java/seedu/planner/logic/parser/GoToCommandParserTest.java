package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.planner.logic.commands.GoToCommand;

//@@author GabrielYik

public class GoToCommandParserTest {

    private GoToCommandParser parser = new GoToCommandParser();

    @Test
    public void parse_validArgs_returnsGoToCommand() {
        assertParseSuccess(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE,
                new GoToCommand(VALID_YEAR_ONE, VALID_SEMESTER_ONE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }
}
