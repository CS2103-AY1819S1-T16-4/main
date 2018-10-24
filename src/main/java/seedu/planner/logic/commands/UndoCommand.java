package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;

/**
 * Reverts the {@code model}'s planner book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoModulePlanner()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoModulePlanner();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
