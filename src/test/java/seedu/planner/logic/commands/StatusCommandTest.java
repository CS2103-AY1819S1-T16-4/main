package seedu.planner.logic.commands;

import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Before;
import org.junit.Test;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.UserPrefs;
import seedu.planner.testutil.ModulePlannerBuilder;

public class StatusCommandTest {

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
    public void check_Foundation_success() {
        StatusCommand statusCommand = new StatusCommand();
        String expectedMessage = "University Level Requirement: 0 (need 20 more)\n"
                + "Foundation: 4 (need 32 more)\n"
                + "Mathematics: 0 (need 12 more)\n"
                + "Science: 0 (need 4 more)\n"
                + "IT Professionalism: 0 (need 12 more)\n"
                + "Industrial Experience Requirement: 0 (need 12 more)\n"
                + "Breadth and Depth (Team Project): 0 (need 16 more)";

        assertCommandSuccess(statusCommand, model, commandHistory, expectedMessage, model);
    }
}
