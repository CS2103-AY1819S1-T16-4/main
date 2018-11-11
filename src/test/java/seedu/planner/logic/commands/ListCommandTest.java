package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_YEAR_FIVE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_TWO;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.planner.logic.commands.CommandTestUtil.showModuleTakenAtIndex;
import static seedu.planner.model.util.IndexUtil.VALUE_NOT_AVAILABLE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ZERO;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.ListModulesEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.UserPrefs;
import seedu.planner.ui.testutil.EventsCollectorRule;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        expectedModel = new ModelManager(model.getModulePlanner(), new UserPrefs());
    }

    @Test
    public void execute_listAllYears_success() {
        ListCommand listCommand = new ListCommand(VALUE_NOT_AVAILABLE);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS_ALL;

        assertCommandSuccess(listCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(showModuleTakenAtIndex(model, INDEX_ZERO),
                     getTypicalModulePlanner().getTakenModules().get(INDEX_ZERO));
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ListModulesEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }

    @Test
    public void execute_validYearContainsModule_success() {
        ListCommand listCommand = new ListCommand(VALID_YEAR_ONE);
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_YEAR, VALID_YEAR_ONE);

        assertCommandSuccess(listCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(showModuleTakenAtIndex(model, INDEX_ZERO),
                     getTypicalModulePlanner().getTakenModules().get(INDEX_ZERO));
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ListModulesEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }

    @Test
    public void execute_validYearContainsNoModule_success() {
        ListCommand listCommand = new ListCommand(VALID_YEAR_TWO);
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_YEAR, VALID_YEAR_TWO);

        assertCommandSuccess(listCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(showModuleTakenAtIndex(model, INDEX_ZERO),
                     null);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ListModulesEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }

    @Test
    public void execute_invalidYear_throwsCommandException() throws CommandException {
        ListCommand listCommand = new ListCommand(INVALID_YEAR_FIVE);

        thrown.expect(CommandException.class);
        thrown.expectMessage(Messages.MESSAGE_INVALID_PARAMETERS);

        listCommand.execute(model, commandHistory);
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
