package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.areEqualIgnoreOrder;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.planner.commons.util.StringUtil.convertCollectionToString;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.AddModuleEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

//@@author RomaRomama

/**
 * Add a module to the module planner
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

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

    public static final String MESSAGE_SUCCESS = "Added Module(s): %1$s";

    private final int semesterIndex;
    private final Set<Module> modulesToAdd;

    /**
     * Add module method
     */
    public AddCommand(Set<Module> modules, int index) {
        requireAllNonNull(modules, index);
        semesterIndex = index;
        modulesToAdd = modules;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Module> invalidModules = new ArrayList<>();
        List<Module> existedModules = new ArrayList<>();

        for (Module m : modulesToAdd) {
            if (!model.isModuleOffered(m)) {
                invalidModules.add(m);
            }

            if (model.hasModule(m)) {
                existedModules.add(m);
            }
        }

        if (!invalidModules.isEmpty()) {
            String errorMessage = String.format(Messages.MESSAGE_INVALID_MODULES,
                    convertCollectionToString(invalidModules));
            throw new CommandException(errorMessage);
        }

        if (!existedModules.isEmpty()) {
            String errorMessage = String.format(Messages.MESSAGE_EXISTED_MODULES,
                    convertCollectionToString(existedModules));
            throw new CommandException(errorMessage);
        }

        model.addModules(modulesToAdd, semesterIndex);
        model.commitModulePlanner();

        EventsCenter.getInstance().post(new AddModuleEvent(semesterIndex));
        String successMessage = String.format(MESSAGE_SUCCESS, convertCollectionToString(modulesToAdd));
        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand command = (AddCommand) other;
        return areEqualIgnoreOrder(modulesToAdd, command.modulesToAdd);
    }
}
