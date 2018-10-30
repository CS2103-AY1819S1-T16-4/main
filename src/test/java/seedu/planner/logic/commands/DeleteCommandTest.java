package seedu.planner.logic.commands;

import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MODULE_CS0000;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.UserPrefs;
import seedu.planner.model.module.Module;
import seedu.planner.testutil.ModulePlannerBuilder;

//@@author GabrielYik

public class DeleteCommandTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        ModulePlanner modulePlanner = new ModulePlannerBuilder()
                .withModule(VALID_MODULE_CS1010)
                .withModule(VALID_MODULE_CS2030)
                .build();
        model = new ModelManager(modulePlanner, new UserPrefs());
    }

    @Test
    public void execute_validModule_success() {
        List<Module> moduleToDelete = List.of(VALID_MODULE_CS1010);
        DeleteCommand deleteCommand = new DeleteCommand(moduleToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MODULES_SUCCESS, moduleToDelete.get(0));
        ModulePlanner modulePlanner = new ModulePlannerBuilder()
                .withModule(VALID_MODULE_CS1010)
                .withModule(VALID_MODULE_CS2030)
                .build();
        Model expectedModel = new ModelManager(modulePlanner, new UserPrefs());
        expectedModel.deleteModules(List.of(VALID_MODULE_CS1010));
        expectedModel.commitModulePlanner();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModule_throwsCommandException() {
        Module moduleToDelete = INVALID_MODULE_CS0000;
        DeleteCommand deleteCommand = new DeleteCommand(List.of(moduleToDelete));

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MODULES, moduleToDelete);

        assertCommandFailure(deleteCommand, model, commandHistory, expectedMessage);
    }

    @Test
    public void execute_validModuleNotYetAdded_throwsCommandException() {
        Module moduleToDelete = VALID_MODULE_CS1231;
        DeleteCommand deleteCommand = new DeleteCommand(List.of(moduleToDelete));

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MODULES, moduleToDelete);

        assertCommandFailure(deleteCommand, model, commandHistory, expectedMessage);
    }

}
