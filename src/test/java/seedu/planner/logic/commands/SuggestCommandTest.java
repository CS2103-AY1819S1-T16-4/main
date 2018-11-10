package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_INDEX_EIGHT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ZERO;

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

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validArgs_success() {
        SuggestCommand suggestCommand = new SuggestCommand(VALID_INDEX_ZERO);

        CommandResult result;
        try {
            result = suggestCommand.execute(model, commandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        assertEquals(SuggestCommand.MESSAGE_SUCCESS, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof SuggestModulesEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }

    @Test
    public void execute_invalidArgs_throwsCommandException() throws CommandException {
        SuggestCommand suggestCommand = new SuggestCommand(INVALID_INDEX_EIGHT);

        thrown.expect(CommandException.class);
        thrown.expectMessage(Messages.MESSAGE_INVALID_PARAMETERS);

        suggestCommand.execute(model, commandHistory);
    }

}
