package seedu.planner.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

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

public class ModulePlannerParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ModulePlannerParser parser = new ModulePlannerParser();

    @Test
    public void parseCommand_add() throws Exception {
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD) instanceof AddCommand);
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + " 3") instanceof AddCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD) instanceof DeleteCommand);
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " 3") instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD) instanceof FindCommand);
        assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + " 3") instanceof FindCommand);
    }

    @Test
    public void parseCommand_goTo() throws Exception {
        assertTrue(parser.parseCommand(GoToCommand.COMMAND_WORD) instanceof GoToCommand);
        assertTrue(parser.parseCommand(GoToCommand.COMMAND_WORD + " 3") instanceof GoToCommand);
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
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_setUp() throws Exception {
        assertTrue(parser.parseCommand(SetUpCommand.COMMAND_WORD) instanceof SetUpCommand);
        assertTrue(parser.parseCommand(SetUpCommand.COMMAND_WORD + " 3") instanceof SetUpCommand);
    }

    @Test
    public void parseCommand_status() throws Exception {
        assertTrue(parser.parseCommand(StatusCommand.COMMAND_WORD) instanceof StatusCommand);
        assertTrue(parser.parseCommand(StatusCommand.COMMAND_WORD + " 3") instanceof StatusCommand);
    }

    @Test
    public void parseCommand_suggest() throws Exception {
        assertTrue(parser.parseCommand(SuggestCommand.COMMAND_WORD) instanceof SuggestCommand);
        assertTrue(parser.parseCommand(SuggestCommand.COMMAND_WORD + " 3") instanceof SuggestCommand);
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
