package seedu.planner.logic.commands;

import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Test;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;

public class SuggestCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_suggest_success() {
        assertCommandSuccess(new SuggestCommand(), model, commandHistory, SuggestCommand.MESSAGE_SUCCESS,
            expectedModel);
    }
}
