package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.planner.model.util.IndexUtil.isValidIndex;

import java.util.logging.Logger;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.SuggestModulesEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;

//@@author Hilda-Ang

/**
 * Shows all modules that the user is available to take for a particular year and semester.
 */
public class SuggestCommand extends Command {

    public static final String COMMAND_WORD = "suggest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show modules the user is available to take. "
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1 "
            + PREFIX_SEMESTER + "1 ";

    public static final String MESSAGE_SUCCESS = "Showed all available modules for specified year and semester.";

    private static Logger logger = LogsCenter.getLogger(SuggestCommand.class);

    private int index;

    /**
     * Creates a SuggestCommand to list modules available for specified year and semester.
     */
    public SuggestCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        logger.info("starting execution of suggest command");
        requireNonNull(model);

        if (!isValidIndex(index)) {
            logger.warning("error in suggest command execution due to invalid index");
            throw new CommandException(Messages.MESSAGE_INVALID_PARAMETERS);
        }

        model.suggestModules(index);
        logger.info("suggested modules for index " + index);
        EventsCenter.getInstance().post(new SuggestModulesEvent(model.getAvailableModules(), index));

        return new CommandResult(String.format(MESSAGE_SUCCESS, index));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SuggestCommand // instanceof handles nulls
                && index == ((SuggestCommand) other).index);
    }
}
