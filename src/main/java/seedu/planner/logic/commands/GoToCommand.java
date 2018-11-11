package seedu.planner.logic.commands;

import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.planner.model.util.IndexUtil.convertYearAndSemesterToIndex;
import static seedu.planner.model.util.IndexUtil.isValidSemester;
import static seedu.planner.model.util.IndexUtil.isValidYear;

import java.util.logging.Logger;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.GoToEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;

//@@author GabrielYik

/**
 * A class representing the {@code goto} command.
 */
public class GoToCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes from one tab to another.\n"
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1" + " "
            + PREFIX_SEMESTER + "2";

    public static final String SHOWING_GOTO_MESSAGE = "Go to Y%1$sS%2$s";

    private static final Logger logger = LogsCenter.getLogger(GoToCommand.class);

    private final int year;
    private final int semester;

    /**
     * Constructs a {@code GoToCommand}.
     *
     * @param year The year to go to
     * @param semester The semester in the year to go to
     */
    public GoToCommand(int year, int semester) {
        this.year = year;
        this.semester = semester;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        if (!isValidYear(year) || !isValidSemester(semester)) {
            logger.fine("In goto command: year " + year + " or semester " + semester + " invalid");
            throw new CommandException(Messages.MESSAGE_INVALID_PARAMETERS);
        }

        int tabIndex = convertYearAndSemesterToIndex(year, semester);
        EventsCenter.getInstance().post(new GoToEvent(tabIndex));
        return new CommandResult(String.format(SHOWING_GOTO_MESSAGE, year, semester));
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof GoToCommand)) {
            return false;
        }

        GoToCommand command = (GoToCommand) other;
        return this.year == command.year && this.semester == command.semester;
    }
}
