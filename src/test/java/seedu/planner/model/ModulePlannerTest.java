package seedu.planner.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ModulePlannerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ModulePlanner modulePlanner = new ModulePlanner();

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modulePlanner.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyModulePlanner_replacesData() {
        ModulePlanner newData = getTypicalModulePlanner();
        modulePlanner.resetData(newData);
        assertEquals(newData, modulePlanner);
    }

    /*
    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        thrown.expect(DuplicatePersonException.class);
        addressBook.resetData(newData);
    }
    */

    @Test
    public void addModules_invalidIndex_throwsIndexOutOfBoundsException() {
        thrown.expect(IndexOutOfBoundsException.class);
        modulePlanner.addModules(getTypicalModules(), 8);
    }

    @Test
    public void addModules_validIndex_success() {
        modulePlanner.addModules(getTypicalModules(), 0);
        assertEquals(new HashSet<>(modulePlanner.getModulesTaken(0)), getTypicalModules());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modulePlanner.hasModule(null);
    }

    @Test
    public void hasModule_moduleNotInModulePlanner_returnsFalse() {
        assertFalse(modulePlanner.hasModule(CS1010));
    }

    @Test
    public void hasModule_moduleInModulePlanner_returnsTrue() {
        modulePlanner.addModules(getTypicalModules(), INDEX_FIRST);
        assertTrue(modulePlanner.hasModule(CS1010));
    }

    @Test
    public void getModulesTaken_sameIndex_returnsSameList() {
        modulePlanner.addModules(getTypicalModules(), 0);
        assertEquals(modulePlanner.getModulesTaken(0), modulePlanner.getModulesTaken(0));
    }

    @Test
    public void getModulesTaken_differentIndex_returnsDifferentList() {
        modulePlanner.addModules(getTypicalModules(), 0);
        assertNotEquals(modulePlanner.getModulesTaken(0), modulePlanner.getModulesTaken(1));
    }

    @Test
    public void getModulesTaken_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modulePlanner.getModulesTaken(0).remove(0);
    }

    @Test
    public void getModulesAvailable_sameModules_returnsSameList() {
        ModulePlanner differentModulePlanner = new ModulePlanner();
        assertEquals(modulePlanner.getAvailableModuleList(), differentModulePlanner.getAvailableModuleList());
    }

    @Test
    public void getModulesAvailable_differentModules_returnsDifferentList() {
        ModulePlanner differentModulePlanner = getTypicalModulePlanner();
        assertNotEquals(modulePlanner.getAvailableModuleList(), differentModulePlanner.getAvailableModuleList());
    }

    @Test
    public void equals() {
        modulePlanner.addModules(getTypicalModules(), 1);
        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), 1);

        // same modules in same semester -> returns true
        assertTrue(modulePlanner.equals(differentModulePlanner));

        // same object -> returns true
        assertTrue(modulePlanner.equals(modulePlanner));

        // null -> returns false
        assertFalse(modulePlanner.equals(null));

        // different types -> returns false
        assertFalse(modulePlanner.equals(5));

        // different modules in different semester -> returns false
        assertFalse(modulePlanner.equals(getTypicalModulePlanner()));

    }

    /*
    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }
    */

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    /*
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }
    */

}
