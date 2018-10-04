package seedu.planner.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.planner.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the planner book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the planner book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the planner book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the planner book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the planner book.
     */
    void updatePerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if the model has previous planner book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone planner book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's planner book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's planner book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current planner book state for undo/redo.
     */
    void commitAddressBook();
}
