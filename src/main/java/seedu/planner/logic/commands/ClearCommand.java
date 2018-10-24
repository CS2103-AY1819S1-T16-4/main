package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModulePlanner;

/**
 * Clears the planner book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Module planner has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.resetData(new ModulePlanner());
        model.commitModulePlanner();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
