package seedu.planner.logic.commands;

import static seedu.planner.commons.util.CollectionUtil.getAnyOne;
import static seedu.planner.commons.util.StringUtil.convertCollectionToString;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MODULE_CS0000;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2040;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Set;

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
    private Model expectedModel;

    @Before
    public void setUp() {
        ModulePlanner mp1 = new ModulePlannerBuilder()
                .withModule(VALID_MODULE_CS1010)
                .withModule(VALID_MODULE_CS1231)
                .withModule(VALID_MODULE_CS2030)
                .build();
        model = new ModelManager(mp1, new UserPrefs());

        ModulePlanner mp2 = new ModulePlannerBuilder()
                .withModule(VALID_MODULE_CS1010)
                .withModule(VALID_MODULE_CS1231)
                .withModule(VALID_MODULE_CS2030)
                .build();
        expectedModel = new ModelManager(mp2, new UserPrefs());
    }

    @Test
    public void execute_validModule_validModuleRemoved() {
        Set<Module> moduleToDelete = Set.of(VALID_MODULE_CS1010);
        DeleteCommand deleteCommand = new DeleteCommand(moduleToDelete);

        String expectedMessage = String.format(
                DeleteCommand.MESSAGE_DELETE_MODULES_SUCCESS, getAnyOne(moduleToDelete).get());
        expectedModel.deleteModules(moduleToDelete);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModules_validModulesRemoved() {
        Set<Module> modulesToDelete = Set.of(VALID_MODULE_CS1010, VALID_MODULE_CS1231);
        DeleteCommand deleteCommand = new DeleteCommand(modulesToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MODULES_SUCCESS,
                convertCollectionToString(modulesToDelete));
        expectedModel.deleteModules(modulesToDelete);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModule_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(Set.of(INVALID_MODULE_CS0000));

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MODULES, INVALID_MODULE_CS0000);

        assertCommandFailure(deleteCommand, model, commandHistory, expectedMessage);
    }

    @Test
    public void execute_validModuleNotYetAdded_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(Set.of(VALID_MODULE_CS2040));

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MODULES, VALID_MODULE_CS2040);

        assertCommandFailure(deleteCommand, model, commandHistory, expectedMessage);
    }

}
