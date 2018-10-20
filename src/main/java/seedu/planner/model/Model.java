package seedu.planner.model;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    //TODO: can have a predicate to filter taken and available modules
    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

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
     * Checks if the module exists.
     *
     * @param module The module
     * @return True if the module with {@code moduleCode} exists, false if not
     */
    boolean hasModule(Module module);

    /**
     * Deletes the modules.
     *
     * @param modules The modules
     */
    void deleteModules(List<Module> modules);

    //@@author RomaRomama

    /**
     * Add list of modules into the specified semester
     *
     * @param modules List of modules
     * @param index Index of the semester
     */
    void addModules(List<Module> modules, int index);

    //@@author

    // @@author rongjiecomputer

    /**
     * Returns an immutable list of {@code ModuleInfo}s.
     * Note: return type might change to ImmutableList<ModuleInfo> in the future.
     */
    ModuleInfo[] getModuleInfo();

    // @@author

    //@@author GabrielYik

    //TODO: confirm filtered or sorted or both
    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredTakenModuleList(int index);

    //TODO: confirm filtered or sorted or both
    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredAvailableModuleList(int index);

    //@@author

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
