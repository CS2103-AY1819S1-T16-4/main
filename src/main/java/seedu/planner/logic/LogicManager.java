package seedu.planner.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.planner.commons.core.ComponentManager;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.logic.commands.Command;
import seedu.planner.logic.commands.CommandResult;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.logic.parser.ModulePlannerParser;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final CommandHistory history;
    private final ModulePlannerParser modulePlannerParser;

    public LogicManager(Model model) {
        this.model = model;
        history = new CommandHistory();
        modulePlannerParser = new ModulePlannerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Command command = modulePlannerParser.parseCommand(commandText);
            return command.execute(model, history);
        } finally {
            history.add(commandText);
        }
    }

    //@@author GabrielYik

    @Override
    public ObservableList<Module> getFilteredTakenModuleList(int index) {
        return model.getFilteredTakenModuleList(index);
    }

    @Override
    public ObservableList<Module> getFilteredAvailableModuleList(int index) {
        return model.getFilteredAvailableModuleList(index);
    }

    //@@author

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
