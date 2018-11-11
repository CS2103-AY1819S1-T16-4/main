package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_INDEX_EIGHT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ZERO;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.planner.logic.commands.CommandTestUtil.showModuleAvailableAtIndex;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ZERO;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.SuggestModulesEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.ui.testutil.EventsCollectorRule;

public class SuggestCommandTest {

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndex_success() {
        SuggestCommand suggestCommand = new SuggestCommand(VALID_INDEX_ZERO);

        assertCommandSuccess(suggestCommand, model, commandHistory, SuggestCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(showModuleAvailableAtIndex(model, INDEX_ZERO),
                     getTypicalModulePlanner().getTakenModules().get(INDEX_ZERO));
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof SuggestModulesEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() throws CommandException {
        SuggestCommand suggestCommand = new SuggestCommand(INVALID_INDEX_EIGHT);

        thrown.expect(CommandException.class);
        thrown.expectMessage(Messages.MESSAGE_INVALID_PARAMETERS);

        suggestCommand.execute(model, commandHistory);
    }

}
