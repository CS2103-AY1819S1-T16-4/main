package seedu.planner.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.planner.commons.core.index.Index;
import seedu.planner.logic.CommandHistory;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.AddressBook;
import seedu.planner.model.Model;
import seedu.planner.model.person.NameContainsKeywordsPredicate;
import seedu.planner.model.person.Person;
import seedu.planner.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_MODULE_CS1010 = "CS1010";
    public static final String VALID_MODULE_CS1231 = "CS1231";

    public static final String MODULE_DESC_CS1010 = " " + PREFIX_CODE + VALID_MODULE_CS1010;
    public static final String MODULE_DESC_CS1231 = " " + PREFIX_CODE + VALID_MODULE_CS1231;

    public static final int VALID_YEAR_ONE = 1;
    public static final int VALID_SEMESTER_ONE = 1;

    public static final String VALID_YEAR_DESC_ONE = " " + PREFIX_YEAR + VALID_YEAR_ONE;
    public static final String VALID_SEMESTER_DESC_ONE = " " + PREFIX_SEMESTER + VALID_SEMESTER_ONE;

    public static final int INVALID_YEAR_FIVE = 5;
    public static final int INVALID_SEMESTER_THREE = 3;

    public static final String INVALID_YEAR_DESC_FIVE = " " + PREFIX_YEAR + INVALID_YEAR_FIVE;
    public static final String INVALID_SEMESTER_DESC_THREE = " " + PREFIX_SEMESTER + INVALID_SEMESTER_THREE;

    public static final String VALID_MAJOR_CS = "Computer Science";
    public static final String VALID_FOCUS_AREA_SE = "Software Engineering";
    public static final String VALID_FOCUS_AREA_PL = "Programming Languages";

    public static final String VALID_MAJOR_DESC_CS = " " + PREFIX_MAJOR + VALID_MAJOR_CS;
    public static final String VALID_FOCUS_AREA_DESC_SE = " " + PREFIX_FOCUS_AREA + VALID_FOCUS_AREA_SE;
    public static final String VALID_FOCUS_AREA_DESC_PL = " " + PREFIX_FOCUS_AREA + VALID_FOCUS_AREA_PL;

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

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
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s planner book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s planner book.
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.commitAddressBook();
    }

}
