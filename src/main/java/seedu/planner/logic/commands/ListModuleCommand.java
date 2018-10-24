package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;

//@@author Hilda-Ang

/**
 * Placeholder for ListModuleCommand.
 * Lists all modules the user has taken or is available to take.
 */
public class ListModuleCommand extends Command {

    public static final String COMMAND_WORD = "listModule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List modules taken or suggested for a semester. "
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1 "
            + PREFIX_SEMESTER + "1 ";

    public static final String MESSAGE_SUCCESS = "Listed all modules taken";

    private int index;

    /**
     * Creates a ListModuleCommand to list modules for specified semester.
     */
    public ListModuleCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.getFilteredTakenModuleList(index);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListModuleCommand // instanceof handles nulls
                && index == ((ListModuleCommand) other).index);
    }
}
