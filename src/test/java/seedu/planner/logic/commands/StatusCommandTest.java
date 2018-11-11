package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import seedu.planner.commons.events.ui.StatusEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.UserPrefs;
import seedu.planner.testutil.ModulePlannerBuilder;
import seedu.planner.ui.testutil.EventsCollectorRule;

public class StatusCommandTest {

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        ModulePlanner modulePlanner = new ModulePlannerBuilder()
                .withModule(VALID_MODULE_CS1010)
                .build();
        model = new ModelManager(modulePlanner, new UserPrefs());
    }

    @Test
    public void execute_statusDisplayed_success() {
        StatusCommand statusCommand = new StatusCommand();

        String expectedMessage = StatusCommand.MESSAGE_SUCCESS;

        CommandResult result = statusCommand.execute(model, commandHistory);

        assertEquals(expectedMessage, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof StatusEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }
}
