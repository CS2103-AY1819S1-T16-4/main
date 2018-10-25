package seedu.planner.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.testutil.Assert.assertThrows;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.CS2030;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.planner.testutil.ModulePlannerBuilder;

public class VersionedModulePlannerTest {

    private final ReadOnlyModulePlanner modulePlannerWithCS1010 = new ModulePlannerBuilder().withModule(CS1010).build();
    private final ReadOnlyModulePlanner modulePlannerWithCS2030 = new ModulePlannerBuilder().withModule(CS2030).build();
    private final ReadOnlyModulePlanner emptyModulePlanner = new ModulePlanner();

    @Test
    public void commit_singleModulePlanner_noStatesRemovedCurrentStateSaved() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(emptyModulePlanner);

        versionedModulePlanner.commit();
        assertModulePlannerListStatus(versionedModulePlanner,
                Collections.singletonList(emptyModulePlanner),
                emptyModulePlanner,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleModulePlannerPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);

        versionedModulePlanner.commit();
        assertModulePlannerListStatus(versionedModulePlanner,
                Arrays.asList(emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030),
                modulePlannerWithCS2030,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleModulePlannerPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 2);

        versionedModulePlanner.commit();
        assertModulePlannerListStatus(versionedModulePlanner,
                Collections.singletonList(emptyModulePlanner),
                emptyModulePlanner,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleModulePlannerPointerAtEndOfStateList_returnsTrue() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);

        assertTrue(versionedModulePlanner.canUndo());
    }

    @Test
    public void canUndo_multipleModulePlannerPointerAtStartOfStateList_returnsTrue() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 1);

        assertTrue(versionedModulePlanner.canUndo());
    }

    @Test
    public void canUndo_singleModulePlanner_returnsFalse() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(emptyModulePlanner);

        assertFalse(versionedModulePlanner.canUndo());
    }

    @Test
    public void canUndo_multipleModulePlannerPointerAtStartOfStateList_returnsFalse() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 2);

        assertFalse(versionedModulePlanner.canUndo());
    }

    @Test
    public void canRedo_multipleModulePlannerPointerNotAtEndOfStateList_returnsTrue() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 1);

        assertTrue(versionedModulePlanner.canRedo());
    }

    @Test
    public void canRedo_multipleModulePlannerPointerAtStartOfStateList_returnsTrue() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 2);

        assertTrue(versionedModulePlanner.canRedo());
    }

    @Test
    public void canRedo_singleModulePlanner_returnsFalse() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(emptyModulePlanner);

        assertFalse(versionedModulePlanner.canRedo());
    }

    @Test
    public void canRedo_multipleModulePlannerPointerAtEndOfStateList_returnsFalse() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);

        assertFalse(versionedModulePlanner.canRedo());
    }

    @Test
    public void undo_multipleModulePlannerPointerAtEndOfStateList_success() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);

        versionedModulePlanner.undo();
        assertModulePlannerListStatus(versionedModulePlanner,
                Collections.singletonList(emptyModulePlanner),
                modulePlannerWithCS1010,
                Collections.singletonList(modulePlannerWithCS2030));
    }

    @Test
    public void undo_multipleModulePlannerPointerNotAtStartOfStateList_success() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 1);

        versionedModulePlanner.undo();
        assertModulePlannerListStatus(versionedModulePlanner,
                Collections.emptyList(),
                emptyModulePlanner,
                Arrays.asList(modulePlannerWithCS1010, modulePlannerWithCS2030));
    }

    @Test
    public void undo_singleModulePlanner_throwsNoUndoableStateException() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(emptyModulePlanner);

        assertThrows(VersionedModulePlanner.NoUndoableStateException.class, versionedModulePlanner::undo);
    }

    @Test
    public void undo_multipleModulePlannerPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 2);

        assertThrows(VersionedModulePlanner.NoUndoableStateException.class, versionedModulePlanner::undo);
    }

    @Test
    public void redo_multipleModulePlannerPointerNotAtEndOfStateList_success() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 1);

        versionedModulePlanner.redo();
        assertModulePlannerListStatus(versionedModulePlanner,
                Arrays.asList(emptyModulePlanner, modulePlannerWithCS1010),
                modulePlannerWithCS2030,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleModulePlannerPointerAtStartOfStateList_success() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 2);

        versionedModulePlanner.redo();
        assertModulePlannerListStatus(versionedModulePlanner,
                Collections.singletonList(emptyModulePlanner),
                modulePlannerWithCS1010,
                Collections.singletonList(modulePlannerWithCS2030));
    }

    @Test
    public void redo_singleModulePlanner_throwsNoRedoableStateException() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(emptyModulePlanner);

        assertThrows(VersionedModulePlanner.NoRedoableStateException.class, versionedModulePlanner::redo);
    }

    @Test
    public void redo_multipleModulePlannerPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(
                emptyModulePlanner, modulePlannerWithCS1010, modulePlannerWithCS2030);

        assertThrows(VersionedModulePlanner.NoRedoableStateException.class, versionedModulePlanner::redo);
    }

    @Test
    public void equals() {
        VersionedModulePlanner versionedModulePlanner = prepareModulePlannerList(modulePlannerWithCS1010,
            modulePlannerWithCS2030);

        // same values -> returns true
        VersionedModulePlanner copy = prepareModulePlannerList(modulePlannerWithCS1010, modulePlannerWithCS2030);
        assertTrue(versionedModulePlanner.equals(copy));

        // same object -> returns true
        assertTrue(versionedModulePlanner.equals(versionedModulePlanner));

        // null -> returns false
        assertFalse(versionedModulePlanner.equals(null));

        // different types -> returns false
        assertFalse(versionedModulePlanner.equals(1));

        // different state list -> returns false
        VersionedModulePlanner differentModulePlannerList = prepareModulePlannerList(modulePlannerWithCS2030,
            emptyModulePlanner);
        assertFalse(versionedModulePlanner.equals(differentModulePlannerList));

        // different current pointer index -> returns false
        VersionedModulePlanner differentCurrentStatePointer = prepareModulePlannerList(
                modulePlannerWithCS1010, modulePlannerWithCS2030);
        shiftCurrentStatePointerLeftwards(versionedModulePlanner, 1);
        assertFalse(versionedModulePlanner.equals(differentCurrentStatePointer));
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
