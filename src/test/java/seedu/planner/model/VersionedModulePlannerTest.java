package seedu.planner.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public class VersionedModulePlannerTest {
    /*
    @Test
    public void commit_singleAddressBook_noStatesRemovedCurrentStateSaved() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(emptyAddressBook);

        versionedAddressBook.commit();
        assertAddressBookListStatus(versionedAddressBook,
                Collections.singletonList(emptyAddressBook),
                emptyAddressBook,
                Collections.emptyList());
    }
    */

    /*
    @Test
    public void commit_multipleAddressBookPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);

        versionedAddressBook.commit();
        assertAddressBookListStatus(versionedAddressBook,
                Arrays.asList(emptyAddressBook, addressBookWithAmy, addressBookWithBob),
                addressBookWithBob,
                Collections.emptyList());
    }
    */

    /*
    @Test
    public void commit_multipleAddressBookPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 2);

        versionedAddressBook.commit();
        assertAddressBookListStatus(versionedAddressBook,
                Collections.singletonList(emptyAddressBook),
                emptyAddressBook,
                Collections.emptyList());
    }
    */

    /*
    @Test
    public void canUndo_multipleAddressBookPointerAtEndOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);

        assertTrue(versionedAddressBook.canUndo());
    }
    */

    /*
    @Test
    public void canUndo_multipleAddressBookPointerAtStartOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 1);

        assertTrue(versionedAddressBook.canUndo());
    }
    */

    /*
    @Test
    public void canUndo_singleAddressBook_returnsFalse() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(emptyAddressBook);

        assertFalse(versionedAddressBook.canUndo());
    }
    */

    /*
    @Test
    public void canUndo_multipleAddressBookPointerAtStartOfStateList_returnsFalse() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 2);

        assertFalse(versionedAddressBook.canUndo());
    }
    */

    /*
    @Test
    public void canRedo_multipleAddressBookPointerNotAtEndOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 1);

        assertTrue(versionedAddressBook.canRedo());
    }
    */

    /*
    @Test
    public void canRedo_multipleAddressBookPointerAtStartOfStateList_returnsTrue() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 2);

        assertTrue(versionedAddressBook.canRedo());
    }
    */

    /*
    @Test
    public void canRedo_singleAddressBook_returnsFalse() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(emptyAddressBook);

        assertFalse(versionedAddressBook.canRedo());
    }
    */

    /*
    @Test
    public void canRedo_multipleAddressBookPointerAtEndOfStateList_returnsFalse() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);

        assertFalse(versionedAddressBook.canRedo());
    }
    */

    /*
    @Test
    public void undo_multipleAddressBookPointerAtEndOfStateList_success() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);

        versionedAddressBook.undo();
        assertAddressBookListStatus(versionedAddressBook,
                Collections.singletonList(emptyAddressBook),
                addressBookWithAmy,
                Collections.singletonList(addressBookWithBob));
    }
    */

    /*
    @Test
    public void undo_multipleAddressBookPointerNotAtStartOfStateList_success() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 1);

        versionedAddressBook.undo();
        assertAddressBookListStatus(versionedAddressBook,
                Collections.emptyList(),
                emptyAddressBook,
                Arrays.asList(addressBookWithAmy, addressBookWithBob));
    }
    */

    /*
    @Test
    public void undo_singleAddressBook_throwsNoUndoableStateException() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(emptyAddressBook);

        assertThrows(VersionedAddressBook.NoUndoableStateException.class, versionedAddressBook::undo);
    }
    */

    /*
    @Test
    public void undo_multipleAddressBookPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 2);

        assertThrows(VersionedAddressBook.NoUndoableStateException.class, versionedAddressBook::undo);
    }
    */

    /*
    @Test
    public void redo_multipleAddressBookPointerNotAtEndOfStateList_success() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 1);

        versionedAddressBook.redo();
        assertAddressBookListStatus(versionedAddressBook,
                Arrays.asList(emptyAddressBook, addressBookWithAmy),
                addressBookWithBob,
                Collections.emptyList());
    }
    */

    /*
    @Test
    public void redo_multipleAddressBookPointerAtStartOfStateList_success() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 2);

        versionedAddressBook.redo();
        assertAddressBookListStatus(versionedAddressBook,
                Collections.singletonList(emptyAddressBook),
                addressBookWithAmy,
                Collections.singletonList(addressBookWithBob));
    }
    */

    /*
    @Test
    public void redo_singleAddressBook_throwsNoRedoableStateException() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(emptyAddressBook);

        assertThrows(VersionedAddressBook.NoRedoableStateException.class, versionedAddressBook::redo);
    }
    */

    /*
    @Test
    public void redo_multipleAddressBookPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(
                emptyAddressBook, addressBookWithAmy, addressBookWithBob);

        assertThrows(VersionedAddressBook.NoRedoableStateException.class, versionedAddressBook::redo);
    }
    */

    @Test
    public void equals() {
        /*
        VersionedAddressBook versionedAddressBook = prepareAddressBookList(addressBookWithAmy, addressBookWithBob);

        // same values -> returns true
        VersionedAddressBook copy = prepareAddressBookList(addressBookWithAmy, addressBookWithBob);
        assertTrue(versionedAddressBook.equals(copy));

        // same object -> returns true
        assertTrue(versionedAddressBook.equals(versionedAddressBook));

        // null -> returns false
        assertFalse(versionedAddressBook.equals(null));

        // different types -> returns false
        assertFalse(versionedAddressBook.equals(1));

        // different state list -> returns false
        VersionedAddressBook differentAddressBookList = prepareAddressBookList(addressBookWithBob, addressBookWithCarl);
        assertFalse(versionedAddressBook.equals(differentAddressBookList));

        // different current pointer index -> returns false
        VersionedAddressBook differentCurrentStatePointer = prepareAddressBookList(
                addressBookWithAmy, addressBookWithBob);
        shiftCurrentStatePointerLeftwards(versionedAddressBook, 1);
        assertFalse(versionedAddressBook.equals(differentCurrentStatePointer));
        */
    }

    /**
     * Asserts that {@code versionedModulePlanner} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedModulePlanner#currentStatePointer} is equal to {@code expectedStatesBeforePointer},
     * and states after {@code versionedModulePlanner#currentStatePointer} is equal to
     * {@code expectedStatesAfterPointer}.
     */
    private void assertModulePlannerListStatus(VersionedModulePlanner versionedModulePlanner,
                                             List<ReadOnlyModulePlanner> expectedStatesBeforePointer,
                                             ReadOnlyModulePlanner expectedCurrentState,
                                             List<ReadOnlyModulePlanner> expectedStatesAfterPointer) {
        // check state currently pointing at is correct
        assertEquals(new ModulePlanner(versionedModulePlanner), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedModulePlanner.canUndo()) {
            versionedModulePlanner.undo();
        }

        // check states before pointer are correct
        for (ReadOnlyModulePlanner expectedModulePlanner : expectedStatesBeforePointer) {
            assertEquals(expectedModulePlanner, new ModulePlanner(versionedModulePlanner));
            versionedModulePlanner.redo();
        }

        // check states after pointer are correct
        for (ReadOnlyModulePlanner expectedModulePlanner : expectedStatesAfterPointer) {
            versionedModulePlanner.redo();
            assertEquals(expectedModulePlanner, new ModulePlanner(versionedModulePlanner));
        }

        // check that there are no more states after pointer
        assertFalse(versionedModulePlanner.canRedo());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedModulePlanner.undo());
    }

    /**
     * Creates and returns a {@code VersionedModulePlanner} with the {@code modulePlannerStates} added into it, and the
     * {@code VersionedModulePlanner#currentStatePointer} at the end of list.
     */
    private VersionedModulePlanner prepareModulePlannerList(ReadOnlyModulePlanner... modulePlannerStates) {
        assertFalse(modulePlannerStates.length == 0);

        VersionedModulePlanner versionedModulePlanner = new VersionedModulePlanner(modulePlannerStates[0]);
        for (int i = 1; i < modulePlannerStates.length; i++) {
            versionedModulePlanner.resetData(modulePlannerStates[i]);
            versionedModulePlanner.commit();
        }

        return versionedModulePlanner;
    }

    /**
     * Shifts the {@code versionedModulePlanner#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(VersionedModulePlanner versionedModulePlanner, int count) {
        for (int i = 0; i < count; i++) {
            versionedModulePlanner.undo();
        }
    }
}
