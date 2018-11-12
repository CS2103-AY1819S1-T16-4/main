package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.commons.core.Messages.MESSAGE_NOT_OFFERED_MODULES;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_CS0000;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.commons.events.ui.FindEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.UserPrefs;
import seedu.planner.model.module.Module;
import seedu.planner.testutil.ModulePlannerBuilder;
import seedu.planner.ui.testutil.EventsCollectorRule;

//@@author GabrielYik

public class FindCommandTest {

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

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
    public void execute_validModule_success() {
        Module moduleToFind = VALID_MODULE_CS1010;
        FindCommand findCommand = new FindCommand(moduleToFind);

        String expectedMessage = String.format(FindCommand.MESSAGE_SUCCESS, moduleToFind.getCode());

        CommandResult result;
        try {
            result = findCommand.execute(model, commandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        assertEquals(expectedMessage, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof FindEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }

    @Test
    public void execute_invalidModule_throwsCommandException() throws CommandException {
        Module moduleToFind = new Module(INVALID_MODULE_CODE_CS0000);
        FindCommand findCommand = new FindCommand(moduleToFind);

        thrown.expect(CommandException.class);
        thrown.expectMessage(String.format(MESSAGE_NOT_OFFERED_MODULES, moduleToFind));

        findCommand.execute(model, commandHistory);
    }
}
