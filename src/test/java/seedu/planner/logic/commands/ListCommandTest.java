package seedu.planner.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_TWO;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;

import org.junit.Before;
import org.junit.Test;

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
    public void execute_validYear_success() {
        ListCommand listCommand = new ListCommand(VALID_YEAR_ONE);
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_YEAR, VALID_YEAR_ONE);

        assertCommandSuccess(listCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listAll_success() {
        ListCommand listCommand = new ListCommand(-1);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS_ALL;

        assertCommandSuccess(listCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ListCommand listFirstCommand = new ListCommand(VALID_YEAR_ONE);
        ListCommand listSecondCommand = new ListCommand(VALID_YEAR_TWO);

        // same object -> returns true
        assertTrue(listFirstCommand.equals(listFirstCommand));

        // same values -> returns true
        ListCommand listFirstCommandCopy = new ListCommand(VALID_YEAR_ONE);
        assertTrue(listFirstCommand.equals(listFirstCommandCopy));

        // different types -> returns false
        assertFalse(listFirstCommand.equals(0));

        // null -> returns false
        assertFalse(listFirstCommand.equals(null));

        // different year -> returns false
        assertFalse(listFirstCommand.equals(listSecondCommand));
    }
}
