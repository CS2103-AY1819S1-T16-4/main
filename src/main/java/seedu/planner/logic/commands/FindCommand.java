package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.getAnyOne;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.Set;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.FindModuleEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

/**
 * A class representing the {@code find} command.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves information about the specified module. "
            + "Parameters: "
            + PREFIX_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CODE + "CS1010 ";

    public static final String MESSAGE_SUCCESS = "Retrieved module information for %1$s";

    private Module moduleToFind;

    public FindCommand(Module moduleToFind) {
        this.moduleToFind = moduleToFind;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!model.isModuleOffered(moduleToFind)) {
            throw new CommandException(String.format(
                    Messages.MESSAGE_INVALID_MODULES, moduleToFind));
        }

        Set<Module> finalizedModules = model.finalizeModules(Set.of(moduleToFind));
        Module finalizedModule = getAnyOne(finalizedModules).get();
        EventsCenter.getInstance().post(new FindModuleEvent(finalizedModule));

        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToFind));
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand command = (FindCommand) other;
        return moduleToFind.equals(command.moduleToFind);
    }
}
