package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.planner.model.util.IndexUtil.isValidIndex;

import javafx.collections.ObservableList;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.ListModuleEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

//@@author Hilda-Ang

/**
 * Lists all modules the user has taken for a specified year and semester.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List modules taken for a semester. "
            + "Parameters: "
            + "[" + PREFIX_YEAR + "YEAR ]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1 ";

    public static final String MESSAGE_SUCCESS_ALL = "Listed all modules taken.";
    public static final String MESSAGE_SUCCESS_YEAR = "Listed all modules taken for year %1$s.";

    private int year;

    /**
     * Creates a ListCommand to list modules for specified semester.
     */
    public ListCommand(int year) {
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (year  == -1) {
            model.listTakenModulesAll();
            ObservableList<Module> modules = model.listModules();
            EventsCenter.getInstance().post(new ListModuleEvent(modules, 0));
            return new CommandResult(MESSAGE_SUCCESS_ALL);
        }

        model.listTakenModulesYear(year);
        ObservableList<Module> modules = model.listModules();
        EventsCenter.getInstance().post(new ListModuleEvent(modules, year));
        return new CommandResult(String.format(MESSAGE_SUCCESS_YEAR, year));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && year == ((ListCommand) other).year);
    }
}
