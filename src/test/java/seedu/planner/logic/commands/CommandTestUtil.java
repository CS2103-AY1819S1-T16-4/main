package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.List;
import java.util.Set;

import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.module.Module;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_MODULE_CODE_CS1010 = "CS1010";
    public static final String VALID_MODULE_CODE_CS1231 = "CS1231";
    public static final String VALID_MODULE_CODE_CS2030 = "CS2030";
    public static final String VALID_MODULE_CODE_CS2040 = "CS2040";
    public static final String VALID_MODULE_CODE_CS2103T = "CS2103T";

    public static final Module VALID_MODULE_CS1010 = new Module(VALID_MODULE_CODE_CS1010);
    public static final Module VALID_MODULE_CS1231 = new Module(VALID_MODULE_CODE_CS1231);
    public static final Module VALID_MODULE_CS2030 = new Module(VALID_MODULE_CODE_CS2030);
    public static final Module VALID_MODULE_CS2040 = new Module(VALID_MODULE_CODE_CS2040);
    public static final Module VALID_MODULE_CS2103T = new Module(VALID_MODULE_CODE_CS2103T);

    public static final String INVALID_MODULE_CODE_CS0000 = "CS0000";

    public static final Module INVALID_MODULE_CS0000 = new Module(INVALID_MODULE_CODE_CS0000);

    public static final String VALID_MODULE_CODE_DESC_CS1010 = " " + PREFIX_CODE + VALID_MODULE_CODE_CS1010;
    public static final String VALID_MODULE_CODE_DESC_CS1231 = " " + PREFIX_CODE + VALID_MODULE_CODE_CS1231;
    public static final String VALID_MODULE_CODE_DESC_CS2030 = " " + PREFIX_CODE + VALID_MODULE_CODE_CS2030;
    public static final String VALID_MODULE_CODE_DESC_CS2040 = " " + PREFIX_CODE + VALID_MODULE_CODE_CS2040;


    public static final int VALID_YEAR_ONE = 1;
    public static final int VALID_YEAR_TWO = 2;
    public static final int VALID_SEMESTER_ONE = 1;

    public static final String VALID_YEAR_DESC_ONE = " " + PREFIX_YEAR + VALID_YEAR_ONE;
    public static final String VALID_SEMESTER_DESC_ONE = " " + PREFIX_SEMESTER + VALID_SEMESTER_ONE;

    public static final int INVALID_YEAR_FIVE = 5;
    public static final int INVALID_SEMESTER_THREE = 3;

    public static final String INVALID_YEAR_DESC_FIVE = " " + PREFIX_YEAR + INVALID_YEAR_FIVE;
    public static final String INVALID_SEMESTER_DESC_THREE = " " + PREFIX_SEMESTER + INVALID_SEMESTER_THREE;

    public static final String VALID_MAJOR_CS = "Computer Science";
    public static final String VALID_FOCUS_AREA_SE = "Software Engineering";
    public static final String VALID_FOCUS_AREA_AI = "Artificial Intelligence";

    public static final String INVALID_MAJOR_SS = "Sleep Science";
    public static final String INVALID_FOCUS_AREA_LD = "Lucid Dreaming";

    public static final String VALID_MAJOR_DESC_CS = " " + PREFIX_MAJOR + VALID_MAJOR_CS;
    public static final String VALID_FOCUS_AREA_DESC_SE = " " + PREFIX_FOCUS_AREA + VALID_FOCUS_AREA_SE;

    public static final int VALID_INDEX_ZERO = 0;
    public static final int VALID_INDEX_ONE = 1;

    public static final int INVALID_INDEX_EIGHT = 8;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the planner book and the filtered person list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ModulePlanner expectedModulePlanner = new ModulePlanner(actualModel.getModulePlanner());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedModulePlanner, actualModel.getModulePlanner());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Deletes the first module in {@code model}'s filtered list from {@code model}'s planner book.
     */
    public static void deleteModule(Model model, Module module) {
        model.deleteModules(Set.of(module));
        model.commitModulePlanner();
    }

    /**
     * Retrieves a {@code Module} from the given index of the list {@code takenModules} in the given model,
     * or returns {@code null} if the size of the list is less than the given index.
     *
     * @param model The given model to be checked in.
     * @param index The given index to retrieve {@code Module} from.
     * @return The {@code Module} at given index, or {@code null} if size of list is less than index.
     */
    public static Module showModuleTakenAtIndex(Model model, int index) {
        List<Module> modulesTaken = model.getTakenModules();
        if (modulesTaken.size() > index) {
            return modulesTaken.get(index);
        }
        return null;
    }

    /**
     * Retrieves a {@code Module} from the given index of the list {@code availableModules} in the given model,
     * or returns {@code null} if the size of the list is less than the given index.
     *
     * @param model The given model to be checked in.
     * @param index The given index to retrieve {@code Module} from.
     * @return The {@code Module} at given index, or {@code null} if size of list is less than index.
     */
    public static Module showModuleAvailableAtIndex(Model model, int index) {
        List<Module> modulesAvailable = model.getAvailableModules();
        if (modulesAvailable.size() > index) {
            return modulesAvailable.get(index);
        }
        return null;
    }
}
