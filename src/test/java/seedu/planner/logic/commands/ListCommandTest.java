package seedu.planner.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_INDEX_EIGHT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ZERO;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;

import org.junit.Before;
import org.junit.Test;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        expectedModel = new ModelManager(model.getModulePlanner(), new UserPrefs());
    }

    @Test
    public void execute_validIndex_success() {
        ListCommand listCommand = new ListCommand(VALID_INDEX_ZERO);
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS, VALID_INDEX_ZERO);

        assertCommandSuccess(listCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ListCommand listCommand = new ListCommand(INVALID_INDEX_EIGHT);
        String expectedMessage = Messages.MESSAGE_INVALID_PARAMETERS;

        assertCommandFailure(listCommand, model, commandHistory, expectedMessage);
    }

    @Test
    public void equals() {
        ListCommand listFirstCommand = new ListCommand(VALID_INDEX_ZERO);
        ListCommand listSecondCommand = new ListCommand(VALID_INDEX_ONE);

        // same object -> returns true
        assertTrue(listFirstCommand.equals(listFirstCommand));

        // same values -> returns true
        ListCommand listFirstCommandCopy = new ListCommand(VALID_INDEX_ZERO);
        assertTrue(listFirstCommand.equals(listFirstCommandCopy));

        // different types -> returns false
        assertFalse(listFirstCommand.equals(0));

        // null -> returns false
        assertFalse(listFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(listFirstCommand.equals(listSecondCommand));
    }
}
