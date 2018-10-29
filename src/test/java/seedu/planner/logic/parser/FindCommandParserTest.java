package seedu.planner.logic.parser;

import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC_CS0000;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MODULE_CS0000;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.planner.logic.commands.FindCommand;

//@@author GabrielYik

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArg_success() {
        FindCommand command = new FindCommand(VALID_MODULE_CS1010);

        assertParseSuccess(parser, VALID_MODULE_CODE_DESC_CS1010, command);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        FindCommand command = new FindCommand(INVALID_MODULE_CS0000);

        assertParseSuccess(parser, INVALID_MODULE_CODE_DESC_CS0000, command);
    }
}
