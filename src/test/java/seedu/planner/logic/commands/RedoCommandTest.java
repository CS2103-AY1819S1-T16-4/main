package seedu.planner.logic.commands;

import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.planner.logic.commands.CommandTestUtil.deleteModule;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;

import org.junit.Before;
import org.junit.Test;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.UserPrefs;

public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of both models' undo/redo history
        deleteModule(model, VALID_MODULE_CS1010);
        deleteModule(model, VALID_MODULE_CS1231);
        model.undoModulePlanner();
        model.undoModulePlanner();

        deleteModule(expectedModel, VALID_MODULE_CS1010);
        deleteModule(expectedModel, VALID_MODULE_CS1231);
        expectedModel.undoModulePlanner();
        expectedModel.undoModulePlanner();
    }

    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoModulePlanner();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoModulePlanner();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
}
