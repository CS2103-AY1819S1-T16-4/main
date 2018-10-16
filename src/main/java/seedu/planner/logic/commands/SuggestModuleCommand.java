package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;

//@@author Hilda-Ang

/**
 * Shows all modules that the user is available to take.
 */
public class SuggestModuleCommand extends Command {

    public static final String COMMAND_WORD = "suggestCommand";
    public static final String MESSAGE_SUCCESS = "Showed all available modules.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        //TODO: Implement command execution

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
