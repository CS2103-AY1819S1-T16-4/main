package seedu.planner.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.model.module.Module;

public class ModelManagerTest {

    // TODO: Extract to TypicalModules and remove this
    private static final Module SAMPLE_MODULE = new Module("CS2103");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasModule(null);
    }

    @Test
    public void hasPerson_personNotInModulePlanner_returnsFalse() {
        assertFalse(modelManager.hasModule(SAMPLE_MODULE));
    }

    @Test
    public void hasPerson_personInModulePlanner_returnsTrue() {
        List<Module> moduleList = new ArrayList<>();
        ((ArrayList) moduleList).add(SAMPLE_MODULE);
        modelManager.addModules(moduleList, 0);
        assertTrue(modelManager.hasModule(SAMPLE_MODULE));
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
        // TODO: User ModulePlannerBuilder()
        List<Module> moduleList = new ArrayList<>();
        ((ArrayList) moduleList).add(SAMPLE_MODULE);
        ModulePlanner modulePlanner = new ModulePlanner();
        modulePlanner.addModules(moduleList, 0);
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
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertTrue(modelManager.equals(new ModelManager(modulePlanner, differentUserPrefs)));
    }
}
