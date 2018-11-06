package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_MAJOR;

import java.util.Set;

import seedu.planner.commons.util.CollectionUtil;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.course.FocusArea;
import seedu.planner.model.course.Major;

/**
 * Sets up the user profile.
 */
public class SetUpCommand extends Command {

    public static final String COMMAND_WORD = "setup";

    public static final String MESSAGE_MAJOR_CONSTRAINTS = "The major should contain only alphabets.";

    public static final String MESSAGE_FOCUS_AREA_CONSTRAINTS = "The focus area should contain only alphabets";

    public static final String MESSAGE_INVALID_MAJOR = "Invalid major (%s)\n";

    public static final String MESSAGE_INVALID_FOCUS_AREAS = "One or more focus area is invalid\n";

    public static final String MESSAGE_SET_UP_SUCCESS = "Set up complete.\n"
            + "Your User Profile\n"
            + "Major: %1$s\n"
            + "Focus Areas(s): %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets up your user profile.\n"
            + "Parameters: "
            + PREFIX_MAJOR + "MAJOR "
            + "[" + PREFIX_FOCUS_AREA + "FOCUS AREA]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MAJOR + "Computer Science "
            + PREFIX_FOCUS_AREA + "Software Engineering "
            + PREFIX_FOCUS_AREA + "Programming Languages";

    private String major;
    private Set<String> focusAreas;

    public SetUpCommand(String major, Set<String> focusAreas) {
        this.major = major;
        this.focusAreas = focusAreas;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        String errorMsg = "";

        if (!Major.hasMajor(major)) {
            errorMsg += String.format(MESSAGE_INVALID_MAJOR, major);
        }

        if (!focusAreas.isEmpty() && !FocusArea.hasFocusAreas(focusAreas)) {
            errorMsg += String.format(MESSAGE_INVALID_FOCUS_AREAS);
        }

        if (errorMsg.length() > 0) {
            throw new CommandException(errorMsg);
        }

        model.setUpUserProfile(major, focusAreas);
        return new CommandResult(String.format(
                MESSAGE_SET_UP_SUCCESS, major, CollectionUtil.convertCollectionToString(focusAreas)));
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
        return this.major.equals(command.major)
                && this.focusAreas.equals(command.focusAreas);
    }
}
