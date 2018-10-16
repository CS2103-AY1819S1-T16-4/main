package seedu.planner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@code ModulePlanner} that keeps track of its own history.
 */
public class VersionedModulePlanner extends ModulePlanner {

    private final List<ReadOnlyModulePlanner> modulePlannerStateList;
    private int currentStatePointer;

    public VersionedModulePlanner(ReadOnlyModulePlanner initialState) {
        super(initialState);

        modulePlannerStateList = new ArrayList<>();
        modulePlannerStateList.add(new ModulePlanner(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code ModulePlanner} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        modulePlannerStateList.add(new ModulePlanner(this));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        modulePlannerStateList.subList(currentStatePointer + 1, modulePlannerStateList.size()).clear();
    }

    /**
     * Restores the planner book to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(modulePlannerStateList.get(currentStatePointer));
    }

    /**
     * Restores the planner book to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(modulePlannerStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has planner book states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has planner book states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < modulePlannerStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedModulePlanner)) {
            return false;
        }

        VersionedModulePlanner otherVersionedAddressBook = (VersionedModulePlanner) other;

        // state check
        return super.equals(otherVersionedAddressBook)
                && modulePlannerStateList.equals(otherVersionedAddressBook.modulePlannerStateList)
                && currentStatePointer == otherVersionedAddressBook.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of addressBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of addressBookState list, unable to redo.");
        }
    }
}
