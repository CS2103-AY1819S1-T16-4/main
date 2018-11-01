package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.Test;

import seedu.planner.logic.commands.DeleteCommand;
import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();
    private Set<Module> moduleSet1 = Set.of(VALID_MODULE_CS1010);
    private Set<Module> moduleSet2 = Set.of(VALID_MODULE_CS1010, VALID_MODULE_CS2030);

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_MODULE_CODE_DESC_CS1010, new DeleteCommand(moduleSet1));
        assertParseSuccess(parser, VALID_MODULE_CODE_DESC_CS1010 + VALID_MODULE_CODE_DESC_CS2030,
                new DeleteCommand(moduleSet2));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
