package seedu.planner.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_FOCUS_AREA_DESC_SE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_FOCUS_AREA_SE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ZERO;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MAJOR_CS;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MAJOR_DESC_CS;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS2040;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.logic.commands.AddCommand;
import seedu.planner.logic.commands.ClearCommand;
import seedu.planner.logic.commands.DeleteCommand;
import seedu.planner.logic.commands.ExitCommand;
import seedu.planner.logic.commands.FindCommand;
import seedu.planner.logic.commands.GoToCommand;
import seedu.planner.logic.commands.HelpCommand;
import seedu.planner.logic.commands.HistoryCommand;
import seedu.planner.logic.commands.ListCommand;
import seedu.planner.logic.commands.RedoCommand;
import seedu.planner.logic.commands.SetUpCommand;
import seedu.planner.logic.commands.StatusCommand;
import seedu.planner.logic.commands.SuggestCommand;
import seedu.planner.logic.commands.UndoCommand;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.Module;

public class ModulePlannerParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ModulePlannerParser parser = new ModulePlannerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Set<Module> modules = Set.of(VALID_MODULE_CS1010, VALID_MODULE_CS2030);
        AddCommand command = (AddCommand) parser.parseCommand(AddCommand.COMMAND_WORD + VALID_YEAR_DESC_ONE
            + VALID_SEMESTER_DESC_ONE + VALID_MODULE_CODE_DESC_CS1010 + VALID_MODULE_CODE_DESC_CS2030);
        assertEquals(new AddCommand(modules, INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Set<Module> modules = getTypicalModules();
        DeleteCommand command = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_WORD
                + VALID_MODULE_CODE_DESC_CS1010
                + VALID_MODULE_CODE_DESC_CS1231
                + VALID_MODULE_CODE_DESC_CS2030
                + VALID_MODULE_CODE_DESC_CS2040);
        assertEquals(new DeleteCommand(modules), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD
            + VALID_MODULE_CODE_DESC_CS1010);
        assertEquals(new FindCommand(CS1010), command);
    }

    @Test
    public void parseCommand_goTo() throws Exception {
        GoToCommand command = (GoToCommand) parser.parseCommand(GoToCommand.COMMAND_WORD
            + VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE);
        assertEquals(new GoToCommand(VALID_YEAR_ONE, VALID_SEMESTER_ONE), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);

        try {
            parser.parseCommand("histories");
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(MESSAGE_UNKNOWN_COMMAND, pe.getMessage());
        }
    }

    @Test
    public void parseCommand_list() throws Exception {
        ListCommand command = (ListCommand) parser.parseCommand(ListCommand.COMMAND_WORD
            + VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE);
        assertEquals(new ListCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_setUp() throws Exception {
        SetUpCommand command = (SetUpCommand) parser.parseCommand(SetUpCommand.COMMAND_WORD
            + VALID_MAJOR_DESC_CS + VALID_FOCUS_AREA_DESC_SE);
        assertEquals(new SetUpCommand(VALID_MAJOR_CS, Set.of(VALID_FOCUS_AREA_SE)),
            command);
    }

    @Test
    public void parseCommand_status() throws Exception {
        assertTrue(parser.parseCommand(StatusCommand.COMMAND_WORD) instanceof StatusCommand);
        assertTrue(parser.parseCommand(StatusCommand.COMMAND_WORD + " 3") instanceof StatusCommand);
    }

    @Test
    public void parseCommand_suggest() throws Exception {
        SuggestCommand command = (SuggestCommand) parser.parseCommand(SuggestCommand.COMMAND_WORD
            + VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE);
        assertEquals(new SuggestCommand(VALID_INDEX_ZERO),
            command);
    }

    @Test
    public void parseCommand_undoCommandWord_returnsUndoCommand() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand("undo 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parseCommand("");
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parseCommand("unknownCommand");
    }
}
