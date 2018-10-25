package seedu.planner.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.testutil.ModulePlannerBuilder;

public class ModelManagerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasModule(null);
    }

    @Test
    public void hasModule_moduleNotInModulePlanner_returnsFalse() {
        assertFalse(modelManager.hasModule(CS1010));
    }

    @Test
    public void hasModule_moduleInModulePlanner_returnsTrue() {
        modelManager.addModules(getTypicalModules(), 0);
        assertTrue(modelManager.hasModule(CS1010));
    }

    /*
    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredPersonList().remove(0);
    }
    */

    @Test
    public void equals() {
        ModulePlanner modulePlanner = new ModulePlannerBuilder().withModule(CS1010).build();
        ModulePlanner differentModulePlanner = new ModulePlanner();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(modulePlanner, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(modulePlanner, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different modulePlanner -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentModulePlanner, userPrefs)));

        // different filteredList -> returns false
        // String[] keywords = ALICE.getName().fullName.split("\\s+");
        // modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        // assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different userPrefs -> returns true
        // UserPrefs differentUserPrefs = new UserPrefs();
        // differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        // assertTrue(modelManager.equals(new ModelManager(modulePlanner, differentUserPrefs)));
    }
}
