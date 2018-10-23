package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.ArrayList;
import java.util.List;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

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

    public static final String MESSAGE_SUCCESS = "Added Module(s): %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module planner";

    private final int semesterIndex;
    private final List<Module> modulesToAdd;

    /**
     * Add module method
     */
    public AddModuleCommand(List<Module> modules, int index) {
        requireAllNonNull(modules, index);
        semesterIndex = index;
        modulesToAdd = modules;
    }

    //TODO
    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Module> invalidModules = new ArrayList<>();
        for (Module m : modulesToAdd) {
            if (!model.isModuleOffered(m)) {
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
        for (Module m : modulesToAdd) {
            sb.append(m.toString() + " ");
        }

        model.addModules(modulesToAdd, semesterIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sb.toString().trim()));
    }
}
