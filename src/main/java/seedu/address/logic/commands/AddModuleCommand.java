package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

//@@author RomaRomama

/**
 * Add a module to the module planner
 */
public class AddModuleCommand extends Command {

    public static final String COMMAND_WORD = "addModule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add current/future modules to the module planner. "
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + PREFIX_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "2 "
            + PREFIX_SEMESTER + "2 "
            + PREFIX_CODE + "CS3244 ";

    public static final String MESSAGE_SUCCESS = "New Module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module planner";

    private final String toAdd;

    /**
     * Add module method
     */
    public AddModuleCommand(String moduleCode) {
        requireNonNull(moduleCode);
        toAdd = moduleCode;
    }

    //TODO
    @Override
    public CommandResult execute(Model model, CommandHistory history) { return null; }
}
