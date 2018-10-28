package seedu.planner.logic.commands;

import static seedu.planner.logic.commands.CommandTestUtil.INVALID_FOCUS_AREA_LD;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MAJOR_SS;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_SEMESTER_THREE;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_YEAR_FIVE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_FOCUS_AREA_PL;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_FOCUS_AREA_SE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MAJOR_CS;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.planner.logic.commands.SetUpCommand.MESSAGE_SET_UP_SUCCESS;

import java.util.Set;

import org.junit.Test;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;

public class SetUpCommandTest {

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validArgs_success() {
        SetUpCommand setUpCommand = new SetUpCommand(VALID_YEAR_ONE, VALID_SEMESTER_ONE,
                VALID_MAJOR_CS, Set.of(VALID_FOCUS_AREA_SE));

        String expectedMessage = String.format(MESSAGE_SET_UP_SUCCESS,
                VALID_YEAR_ONE, VALID_SEMESTER_ONE, VALID_MAJOR_CS, VALID_FOCUS_AREA_SE);
        Model expectedModel = new ModelManager();
        expectedModel.setUpUserProfile(VALID_YEAR_ONE, VALID_SEMESTER_ONE,
                VALID_MAJOR_CS, Set.of(VALID_FOCUS_AREA_SE));

        assertCommandSuccess(setUpCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validArgsMultipleFocusAreas_success() {
        SetUpCommand setUpCommand = new SetUpCommand(VALID_YEAR_ONE, VALID_SEMESTER_ONE,
                VALID_MAJOR_CS, Set.of(VALID_FOCUS_AREA_SE, VALID_FOCUS_AREA_PL));

        String expectedMessage = String.format(MESSAGE_SET_UP_SUCCESS,
                VALID_YEAR_ONE, VALID_SEMESTER_ONE, VALID_MAJOR_CS,
                VALID_FOCUS_AREA_SE + " " + VALID_FOCUS_AREA_PL);
        Model expectedModel = new ModelManager();
        expectedModel.setUpUserProfile(VALID_YEAR_ONE, VALID_SEMESTER_ONE,
                VALID_MAJOR_CS, Set.of(VALID_FOCUS_AREA_SE, VALID_FOCUS_AREA_PL));

        assertCommandSuccess(setUpCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validArgsNoFocusAreas_success() {
        SetUpCommand setUpCommand = new SetUpCommand(VALID_YEAR_ONE, VALID_SEMESTER_ONE,
                VALID_MAJOR_CS, Set.of());

        String expectedMessage = String.format(MESSAGE_SET_UP_SUCCESS,
                VALID_YEAR_ONE, VALID_SEMESTER_ONE, VALID_MAJOR_CS, "");
        Model expectedModel = new ModelManager();
        expectedModel.setUpUserProfile(VALID_YEAR_ONE, VALID_SEMESTER_ONE,
                VALID_MAJOR_CS, Set.of());

        assertCommandSuccess(setUpCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidArgs_throwsCommandException() {
        SetUpCommand setUpCommand = new SetUpCommand(INVALID_YEAR_FIVE, INVALID_SEMESTER_THREE,
                INVALID_MAJOR_SS, Set.of(INVALID_FOCUS_AREA_LD));

        String expectedMessage = Messages.MESSAGE_INVALID_PARAMETERS;

        assertCommandFailure(setUpCommand, model, commandHistory, expectedMessage);
    }

    @Test
    public void execute_oneInvalidArg_throwsCommandException() {
        SetUpCommand setUpCommand = new SetUpCommand(INVALID_YEAR_FIVE, VALID_SEMESTER_ONE,
                VALID_MAJOR_CS, Set.of(VALID_FOCUS_AREA_SE));

        String expectedMessage = Messages.MESSAGE_INVALID_PARAMETERS;

        assertCommandFailure(setUpCommand, model, commandHistory, expectedMessage);
    }
}
