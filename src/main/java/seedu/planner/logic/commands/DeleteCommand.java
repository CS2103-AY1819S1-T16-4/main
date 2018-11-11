package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.areEqualIgnoreOrder;
import static seedu.planner.commons.util.CollectionUtil.formatMessage;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.planner.commons.core.LogsCenter;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * Deletes a module identified using its module code from the module planner.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified using it's module code.\n"
            + "Parameters: "
            + PREFIX_CODE + "MODULE CODE... "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CODE + "CS2103T ";

    public static final String MESSAGE_DELETE_MODULES_SUCCESS = "Deleted Module(s): %1$s";
    public static final String MESSAGE_NON_EXISTENT_MODULES = "Non-existent modules: %1$s";

    private static final Logger logger = LogsCenter.getLogger(DeleteCommand.class);

    private final Set<Module> modulesToDelete;

    private String message;

    public DeleteCommand(Set<Module> modules) {
        modulesToDelete = new HashSet<>(modules);
        message = "";
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        assert message.equals("");

        removeNonExistentModules(model);

        model.deleteModules(modulesToDelete);
        model.commitModulePlanner();

        String successMessage = formatMessage(MESSAGE_DELETE_MODULES_SUCCESS, modulesToDelete);
        message = successMessage + "\n" + message;

        return new CommandResult(message.trim());
    }

    /**
     * Removes the modules that are not in the {@code model}.
     * The modules are checked against the {@code model} to see if they
     * are present.
     *
     * @param model The model
     * @throws CommandException if all modules to be deleted do not exist
     */
    private void removeNonExistentModules(Model model) throws CommandException {
        List<Module> nonExistentModules = collectNonExistentModules(model);
        if (!nonExistentModules.isEmpty()) {
            boolean areAllModulesNonExistent = nonExistentModules.size() == modulesToDelete.size();
            message += formatMessage(MESSAGE_NON_EXISTENT_MODULES, nonExistentModules) + "\n";
            if (areAllModulesNonExistent) {
                logger.fine("In delete command: " + nonExistentModules + " non existent");
                throw new CommandException(message.trim());
            } else {
                modulesToDelete.removeAll(nonExistentModules);
            }
        }
    }


    /**
     * Collects the modules that do not exist in {@code model}.
     *
     * @param model The model where the modules are stored
     * @return The modules that do not exist
     */
    private List<Module> collectNonExistentModules(Model model) {
        List<Module> nonExistentModules = new ArrayList<>();
        for (Module m : modulesToDelete) {
            if (!model.hasModule(m)) {
                nonExistentModules.add(m);
            }
        }
        return nonExistentModules;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand command = (DeleteCommand) other;
        return areEqualIgnoreOrder(modulesToDelete, command.modulesToDelete);
    }
}
