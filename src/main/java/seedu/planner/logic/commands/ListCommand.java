package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.planner.model.util.IndexUtil.VALUE_NOT_AVAILABLE;
import static seedu.planner.model.util.IndexUtil.isValidYear;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.ListModulesEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;

import java.util.logging.Logger;

//@@author Hilda-Ang

/**
 * Lists all modules the user has taken for all years or for a specific year.
 */
public class ListCommand extends Command {

    private static Logger logger = LogsCenter.getLogger(SuggestCommand.class);

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List modules taken for all years "
            + "or for a specific year. "
            + "Parameters: "
            + "[" + PREFIX_YEAR + "YEAR]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1 ";

    public static final String MESSAGE_SUCCESS_ALL = "Listed all modules taken.";
    public static final String MESSAGE_SUCCESS_YEAR = "Listed all modules taken for year %1$s.";

    private int year;

    /**
     * Creates a ListCommand to list taken modules for all years or for a specific year.
     */
    public ListCommand(int year) {
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        logger.info( "starting execution of list command");
        requireNonNull(model);

        // Lists modules taken for all years if no parameter year is supplied.
        if (year == VALUE_NOT_AVAILABLE) {
            logger.info("listing modules for all years");
            model.listTakenModulesAll();
            EventsCenter.getInstance().post(new ListModulesEvent(year));
            return new CommandResult(MESSAGE_SUCCESS_ALL);
        }

        if (!isValidYear(year)) {
            logger.warning("list command execution error due to invalid year");
            throw new CommandException(Messages.MESSAGE_INVALID_PARAMETERS);
        }

        // Lists modules taken for a specific year if a valid year is supplied.
        logger.info("listing modules for year " + year);
        model.listTakenModulesForYear(year);
        EventsCenter.getInstance().post(new ListModulesEvent(year));
        return new CommandResult(String.format(MESSAGE_SUCCESS_YEAR, year));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && year == ((ListCommand) other).year);
    }
}
