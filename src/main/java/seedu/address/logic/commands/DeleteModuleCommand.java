package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

//@@author GabrielYik

/**
 * Deletes a module identified using it's module code from the module planner.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "deleteModule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified using it's module code.\n"
            + "Parameters: "
            + PREFIX_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CODE + "CS2103T ";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final String moduleCode;

    public DeleteModuleCommand(String moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    //TODO
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return null;
    }
}
