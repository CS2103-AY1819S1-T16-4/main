package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_SEMESTER_THREE;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_YEAR_FIVE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.commands.GoToCommand.SHOWING_GOTO_MESSAGE;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.commons.core.Messages;
import seedu.planner.commons.events.ui.GoToEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.ui.testutil.EventsCollectorRule;

//@@author GabrielYik

public class GoToCommandTest {

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validYearAndSemester_success() {
        GoToCommand goToCommand = new GoToCommand(VALID_YEAR_ONE, VALID_SEMESTER_ONE);

        String expectedMessage = String.format(SHOWING_GOTO_MESSAGE, VALID_YEAR_ONE, VALID_SEMESTER_ONE);

        CommandResult result;
        try {
            result = goToCommand.execute(model, commandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        assertEquals(expectedMessage, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof GoToEvent);
        assertEquals(eventsCollectorRule.eventsCollector.getSize(), 1);
    }

    @Test
    public void execute_invalidYearAndSemester_throwsCommandException() throws CommandException {
        GoToCommand goToCommand = new GoToCommand(INVALID_YEAR_FIVE, INVALID_SEMESTER_THREE);

        String expectedMessage = Messages.MESSAGE_INVALID_PARAMETERS;

        thrown.expect(CommandException.class);
        thrown.expectMessage(expectedMessage);

        goToCommand.execute(model, commandHistory);
    }

    @Test
    public void execute_invalidYearAndValidSemester_throwsCommandException() throws CommandException {
        GoToCommand goToCommand = new GoToCommand(INVALID_YEAR_FIVE, VALID_SEMESTER_ONE);

        String expectedMessage = Messages.MESSAGE_INVALID_PARAMETERS;

        thrown.expect(CommandException.class);
        thrown.expectMessage(expectedMessage);

        goToCommand.execute(model, commandHistory);
    }

    @Test
    public void execute_validYearAndInvalidSemester_throwsCommandException() throws CommandException {
        GoToCommand goToCommand = new GoToCommand(VALID_YEAR_ONE, INVALID_SEMESTER_THREE);

        String expectedMessage = Messages.MESSAGE_INVALID_PARAMETERS;

        thrown.expect(CommandException.class);
        thrown.expectMessage(expectedMessage);

        goToCommand.execute(model, commandHistory);
    }
}
