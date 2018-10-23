package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_FOCUS_AREA_DESC_SE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_FOCUS_AREA_SE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MAJOR_CS;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MAJOR_DESC_CS;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.Test;

import seedu.planner.logic.commands.SetUpCommand;

public class SetUpCommandParserTest {

    private SetUpCommandParser parser = new SetUpCommandParser();

    @Test
    public void parse_validArgs_returnsGoToCommand() {
        assertParseSuccess(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE
                + VALID_MAJOR_DESC_CS + VALID_FOCUS_AREA_DESC_SE,
                new SetUpCommand(VALID_YEAR_ONE, VALID_SEMESTER_ONE,
                        VALID_MAJOR_CS, Set.of(VALID_FOCUS_AREA_SE)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SetUpCommand.MESSAGE_USAGE));
    }
}
