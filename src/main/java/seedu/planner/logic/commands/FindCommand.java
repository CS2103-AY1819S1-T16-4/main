package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.List;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.FindModuleEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves information about the specified module. "
            + "Parameters: "
            + PREFIX_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CODE + "CS1010 ";

    public static final String MESSAGE_SUCCESS = "Retrieved module information";

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

        Module finalizedModule = model.finalizeModules(List.of(moduleToFind)).get(0);
        EventsCenter.getInstance().post(new FindModuleEvent(finalizedModule));

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
