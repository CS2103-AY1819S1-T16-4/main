package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.MODULE_DESC_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.MODULE_DESC_CS1231;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.Test;

import seedu.planner.logic.commands.DeleteModuleCommand;
import seedu.planner.model.module.Module;
import seedu.planner.testutil.SampleModules;

//@@author GabrielYik

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteModuleCommandParserTest {

    private DeleteModuleCommandParser parser = new DeleteModuleCommandParser();
    private List<Module> list1 = SampleModules.getModules(0, 1);
    private List<Module> list2 = SampleModules.getModules(0, 2);

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, MODULE_DESC_CS1010, new DeleteModuleCommand(list1));
        assertParseSuccess(parser, MODULE_DESC_CS1010 + MODULE_DESC_CS1231, new DeleteModuleCommand(list2));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));
    }
}
