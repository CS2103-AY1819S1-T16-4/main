package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.logic.parser.ArgumentMultimap;

//@@author rongjiecomputer

/**
 * Add a module to the module planner
 */
public class SetupCommand extends Command {

    public static final String COMMAND_WORD = "setup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set up the profile of the user to allow personalisation. Year and Se"
            + "Parameters: "
            + PREFIX_MAJOR + "MAJOR "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + PREFIX_FOCUS_AREA + "FOCUS_AREA"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MAJOR + "CS "
            + PREFIX_YEAR + "1 "
            + PREFIX_SEMESTER + "1 ";

    // public static final String MESSAGE_SUCCESS = "New Module added: %1$s";
    public static final String MESSAGE_NO_CHANGE = "Nothing has been updated.";

    private final ArgumentMultimap argMultimap;

    public SetupCommand(ArgumentMultimap argMultimap) {
        this.argMultimap = argMultimap;
    }

    // TODO(rongjiecomputer) Implement execute
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return null;
    }
}
