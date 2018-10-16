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

    private final int year;
    private final int semester;

    /**
     * Creates a ListModuleCommand to list modules for specified semester.
     */
    public ListModuleCommand(int index) {
        //TODO
    }

    //TODO
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.getFilteredTakenModuleList(year, semester);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
