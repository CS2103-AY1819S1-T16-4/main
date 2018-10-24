package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;

/**
 * Reverts the {@code model}'s planner book to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoModulePlanner()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoModulePlanner();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
