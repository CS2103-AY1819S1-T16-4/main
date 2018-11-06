package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.core.Messages.MESSAGE_NON_EXISTENT_MODULES;
import static seedu.planner.commons.core.Messages.MESSAGE_NOT_OFFERED_MODULES;
import static seedu.planner.commons.util.CollectionUtil.areEqualIgnoreOrder;
import static seedu.planner.commons.util.CollectionUtil.formatMessage;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * Deletes a module identified using it's module code from the module planner.
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

    private final Set<Module> modulesToDelete;

    private String message;

    public DeleteCommand(Set<Module> modules) {
        modulesToDelete = modules;
        message = "";
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        removeNotOfferedModules(model);
        removeNonExistentModules(model);

        model.deleteModules(modulesToDelete);
        model.commitModulePlanner();

        String successMessage = formatMessage(MESSAGE_DELETE_MODULES_SUCCESS, modulesToDelete);
        message = successMessage + "\n" + message;
        return new CommandResult(message.trim());
    }

    /**
     * Removes the modules not offered by the {@code model}.
     * The modules are checked against the {@code model} to see if they
     * are offered.
     *
     * @param model The model
     * @throws CommandException if all modules to be deleted are not offered
     */
    private void removeNotOfferedModules(Model model) throws CommandException {
        List<Module> notOfferedModules = collectNotOfferedModules(model);
        if (!notOfferedModules.isEmpty()) {
            boolean areAllModulesNotOffered = notOfferedModules.size() == modulesToDelete.size();
            message += formatMessage(MESSAGE_NOT_OFFERED_MODULES, notOfferedModules) + "\n";
            if (areAllModulesNotOffered) {
                throw new CommandException(message.trim());
            } else {
                modulesToDelete.removeAll(notOfferedModules);
            }
        }
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
                throw new CommandException(message.trim());
            } else {
                modulesToDelete.removeAll(nonExistentModules);
            }
        }
    }

    /**
     * Collects the modules that are not offered.
     * These modules are checked against {@code model}.
     *
     * @param model The model
     * @return The modules that are not offered
     */
    private List<Module> collectNotOfferedModules(Model model) {
        List<Module> notOfferedModules = new ArrayList<>();
        for (Module m : modulesToDelete) {
            if (!model.isModuleOffered(m)) {
                notOfferedModules.add(m);
            }
        }
        return notOfferedModules;
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
