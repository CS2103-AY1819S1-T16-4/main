package seedu.planner.logic.commands;

import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_CS0000;
import static seedu.planner.logic.commands.CommandTestUtil.INVALID_MODULE_CS0000;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_INDEX_ZERO;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.HashSet;
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

//@@author RomaRomama

public class AddCommandTest {

    private Model model;
    private Model expectedModel;
    private Set<Module> moduleToAdd = new HashSet<>();
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        ModulePlanner modulePlanner = new ModulePlannerBuilder()
                .withModule(VALID_MODULE_CS1010)
                .build();
        model = new ModelManager(modulePlanner, new UserPrefs());
        expectedModel = new ModelManager(modulePlanner, new UserPrefs());
    }

    @Test
    public void execute_emptyPrerequisiteModuleAdded_success() {
        //Add Module with No prerequisite.
        moduleToAdd.add(new Module("GER1000"));
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ZERO);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, "GER1000");

        expectedModel.addModules(moduleToAdd, VALID_INDEX_ZERO);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonEmptyPrerequisiteModuleAdded_success() {
        //Add Module with prerequisite.
        moduleToAdd.add(VALID_MODULE_CS2030);
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ONE);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, VALID_MODULE_CODE_CS2030);

        expectedModel.addModules(moduleToAdd, VALID_INDEX_ONE);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleNotAdded_success() {
        //Doesn't add invalid modules and mentions which modules are invalid.
        moduleToAdd.add(INVALID_MODULE_CS0000);
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ZERO);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, "")
                + "\n" + String.format(Messages.MESSAGE_INVALID_MODULES, INVALID_MODULE_CODE_CS0000);

        expectedModel.addModules(new HashSet<>(), VALID_INDEX_ZERO);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existedModuleNotAdded_success() {
        //Doesn't add existing modules into the planner and mentions which modules have existed.
        moduleToAdd.add(VALID_MODULE_CS1010);
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ONE);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, "")
                + "\n" + String.format(AddCommand.MESSAGE_EXISTED_MODULES, VALID_MODULE_CODE_CS1010);

        expectedModel.addModules(new HashSet<>(), VALID_INDEX_ONE);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_precludedModuleNotAdded_success() {
        //Doesn't add precluded modules into the planner and mentions which modules have its preclusion taken.
        moduleToAdd.add(new Module("CS1010E"));
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ZERO);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, "")
                + "\n" + String.format(AddCommand.MESSAGE_PRECLUDED_MODULES, "CS1010E");

        expectedModel.addModules(new HashSet<>(), VALID_INDEX_ZERO);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_equivalentModulesNotAdded_success() {
        //Doesn't add equivalent modules into the planner and mentions which modules are equivalent.
        moduleToAdd.add(VALID_MODULE_CS1231);
        moduleToAdd.add(new Module("MA1100"));
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ONE);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, "")
                + "\n" + String.format(AddCommand.MESSAGE_EQUIVALENT, "(CS1231 MA1100)");

        expectedModel.addModules(new HashSet<>(), VALID_INDEX_ONE);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfulfilledModuleNotAdded_success() {
        //Doesn't add modules with unfulfilled prerequisite in the planner and
        //mentions which modules are they.
        moduleToAdd.add(VALID_MODULE_CS2030);
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ZERO);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, "")
                + "\n" + String.format(AddCommand.MESSAGE_UNFULFILLED, VALID_MODULE_CODE_CS2030);

        expectedModel.addModules(new HashSet<>(), VALID_INDEX_ZERO);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allPosibilities_success() {
        //Do all the above simultaneously
        moduleToAdd.add(new Module("MA1301"));
        moduleToAdd.add(new Module("CS1010E"));
        moduleToAdd.add(INVALID_MODULE_CS0000);
        moduleToAdd.add(new Module("MA1301X"));
        moduleToAdd.add(new Module ("CS3230"));
        moduleToAdd.add(VALID_MODULE_CS2030);
        moduleToAdd.add(new Module("GER1000"));
        moduleToAdd.add(VALID_MODULE_CS1010);
        AddCommand addCommand = new AddCommand(moduleToAdd, VALID_INDEX_ONE);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, VALID_MODULE_CODE_CS2030 + " GER1000")
                + "\n" + String.format(Messages.MESSAGE_INVALID_MODULES, INVALID_MODULE_CODE_CS0000)
                + "\n" + String.format(AddCommand.MESSAGE_EXISTED_MODULES, VALID_MODULE_CODE_CS1010)
                + "\n" + String.format(AddCommand.MESSAGE_PRECLUDED_MODULES, "CS1010E")
                + "\n" + String.format(AddCommand.MESSAGE_EQUIVALENT, "(MA1301 MA1301X)")
                + "\n" + String.format(AddCommand.MESSAGE_UNFULFILLED, "CS3230");

        expectedModel.addModules(Set.of(VALID_MODULE_CS2030, new Module("GER1000")), VALID_INDEX_ONE);
        expectedModel.commitModulePlanner();

        assertCommandSuccess(addCommand, model, commandHistory, expectedMessage, expectedModel);
    }
}
