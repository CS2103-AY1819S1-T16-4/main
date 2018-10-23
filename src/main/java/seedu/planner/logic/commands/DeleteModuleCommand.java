package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.ArrayList;
import java.util.List;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

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
            + PREFIX_CODE + "CS2103T";

    public static final String MESSAGE_DELETE_MODULES_SUCCESS = "Deleted Module(s): %1$s";

    private final List<Module> modulesToDelete;

    public DeleteModuleCommand(List<Module> modules) {
        this.modulesToDelete = modules;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Module> invalidModules = new ArrayList<>();
        for (Module m : modulesToDelete) {
            if (!model.hasModule(m)) {
                invalidModules.add(m);
            }
        }

        if (!invalidModules.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Module m : invalidModules) {
                sb.append(m.toString() + " ");
            }
            throw new CommandException(String.format(
                    Messages.MESSAGE_INVALID_MODULES, sb.toString().trim()));
        }

        StringBuilder sb = new StringBuilder();
        for (Module m : modulesToDelete) {
            sb.append(m.toString() + " ");
        }

        model.deleteModules(modulesToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULES_SUCCESS, sb.toString().trim()));
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteModuleCommand)) {
            return false;
        }

        DeleteModuleCommand command = (DeleteModuleCommand) other;
        return modulesToDelete.stream().allMatch(x ->
            command.modulesToDelete.stream().anyMatch(y -> y.equals(x)));
    }
}
