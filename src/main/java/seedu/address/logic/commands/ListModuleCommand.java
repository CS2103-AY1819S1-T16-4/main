package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

/**
 * Placeholder for ListModuleCommand.
 * Lists all modules the user has taken or is available to take.
 *
 * @author Hilda-Ang
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

    /**
     * Creates a ListModuleCommand to list modules for specified semester.
     */
    public ListModuleCommand(String year, String semester) {
        //TODO
    }

    //TODO
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return null;
    }
}
