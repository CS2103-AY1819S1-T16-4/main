package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Set;

import seedu.planner.commons.core.Messages;
import seedu.planner.commons.util.StringUtil;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.util.IndexUtil;

/**
 * Sets up the user profile.
 */
public class SetUpCommand extends Command {

    public static final String COMMAND_WORD = "setup";

    public static final String MESSAGE_MAJOR_CONSTRAINTS = "The major should contain only alphabets.";

    public static final String MESSAGE_FOCUS_AREA_CONSTRAINTS = "The focus area should contain only alphabets";

    public static final String MESSAGE_SET_UP_SUCCESS = "Set up complete.\n"
            + "Your User Profile\n"
            + "Year: %1$d | Semester: %2$d\n"
            + "Major: %3$s\n"
            + "Focus Areas(s): %4$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets up your user profile.\n"
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + PREFIX_MAJOR + "MAJOR "
            + "[" + PREFIX_FOCUS_AREA + "FOCUS AREA]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "1 "
            + PREFIX_SEMESTER + "1 "
            + PREFIX_MAJOR + "Computer Science "
            + PREFIX_FOCUS_AREA + "Software Engineering "
            + PREFIX_FOCUS_AREA + "Programming Languages";

    private int year;
    private int semester;
    private String major;
    private Set<String> focusAreas;

    public SetUpCommand(int year, int semester, String major, Set<String> focusAreas) {
        this.year = year;
        this.semester = semester;
        this.major = major;
        this.focusAreas = focusAreas;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!IndexUtil.isValidYear(year) || !IndexUtil.isValidSemester(semester)
                || !model.hasMajor(major) || !model.hasFocusAreas(focusAreas)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PARAMETERS);
        }

        model.setUpUserProfile(year, semester, major, focusAreas);
        return new CommandResult(String.format(
                MESSAGE_SET_UP_SUCCESS, year, semester, major, StringUtil.convertCollectionToString(focusAreas)));
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof SetUpCommand)) {
            return false;
        }

        SetUpCommand command = (SetUpCommand) other;
        return this.year == command.year
                && this.semester == command.semester
                && this.major.equals(command.major)
                && this.focusAreas.equals(command.focusAreas);
    }
}
