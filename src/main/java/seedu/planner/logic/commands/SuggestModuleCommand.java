package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;

//@@author Hilda-Ang

/**
 * Shows all modules that the user is available to take for a particular semester.
 */
public class SuggestModuleCommand extends Command {

    public static final String COMMAND_WORD = "suggestCommand";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show modules the user is available to take. "
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1 "
            + PREFIX_SEMESTER + "1 ";

    public static final String MESSAGE_SUCCESS = "Showed all available modules.";

    private int index;

    public SuggestModuleCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.getFilteredAvailableModuleList(index);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
