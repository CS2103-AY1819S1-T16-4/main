package seedu.planner.logic.commands;

import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;

import org.junit.Test;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.UserPrefs;

public class ClearCommandTest {

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyModulePlanner_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitModulePlanner();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyModulePlanner_success() {
        Model model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        expectedModel.resetData(new ModulePlanner());
        expectedModel.commitModulePlanner();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
