package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.planner.model.util.IndexUtil.VALUE_NOT_AVAILABLE;
import static seedu.planner.model.util.IndexUtil.isValidYear;

import java.util.logging.Logger;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.ListEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;

//@@author Hilda-Ang

/**
 * Lists all modules the user has taken for all years or for a specific year.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List modules taken for all years "
            + "or for a specific year. "
            + "Parameters: "
            + "[" + PREFIX_YEAR + "YEAR]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1 ";

    public static final String MESSAGE_SUCCESS_ALL = "Listed all modules taken.";
    public static final String MESSAGE_SUCCESS_YEAR = "Listed all modules taken for year %1$s.";

    private static Logger logger = LogsCenter.getLogger(ListCommand.class);

    private int year;

    /**
     * Creates a ListCommand to list taken modules for all years or for a specific year.
     */
    public ListCommand(int year) {
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        logger.info("starting execution of list command");
        requireNonNull(model);

        // Lists modules taken for all years if no parameter year is supplied.
        if (year == VALUE_NOT_AVAILABLE) {
            model.listTakenModulesAll();
            logger.info("listed modules for all years");
            EventsCenter.getInstance().post(new ListEvent(year));
            return new CommandResult(MESSAGE_SUCCESS_ALL);
        }

        if (!isValidYear(year)) {
            logger.warning("error in list command execution due to invalid year");
            throw new CommandException(Messages.MESSAGE_INVALID_PARAMETERS);
        }

        // Lists modules taken for a specific year if a valid year is supplied.
        model.listTakenModulesForYear(year);
        logger.info("listed modules for year " + year);
        EventsCenter.getInstance().post(new ListEvent(year));
        return new CommandResult(String.format(MESSAGE_SUCCESS_YEAR, year));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && year == ((ListCommand) other).year);
    }
}
