package seedu.planner.model;

import java.util.List;
import java.util.Set;
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

    /**
     * Sets up the user profile.
     *
     * @param year The year of study
     * @param semester The semester in the year of study
     * @param major The major
     * @param focusAreas The focus areas
     */
    void setUpUserProfile(int year, int semester, String major, Set<String> focusAreas);

    /**
     * Checks if the major exists.
     * The major is checked against a list of available
     * majors offered by the relevant educational institution.
     *
     * @param major The major
     * @return True if the major is offered, else false
     */
    boolean hasMajor(String major);

    /**
     * Checks if the focus area exists.
     * The focus area is checked against a list of available
     * focus areas offered by the relevant educational institution.
     *
     * @param focusArea The focus area
     * @return True if the focus area is offered, else false
     */
    boolean hasFocusAreas(Set<String> focusArea);

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyModulePlanner newData);

    /** Returns the AddressBook */
    ReadOnlyModulePlanner getModulePlanner();

    /**
     * Checks if the module exists.
     *
     * @param module The module
     * @return True if the module with {@code moduleCode} exists, false if not
     */
    boolean hasModule(Module module);

    /**
     * Checks if the {@code Module} is offered by the
     * relevant educational institution.
     *
     * @param module The module
     * @return True if the module is offered, else false
     */
    boolean isModuleOffered(Module module);

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
    boolean canUndoModulePlanner();

    /**
     * Returns true if the model has undone planner book states to restore.
     */
    boolean canRedoModulePlanner();

    /**
     * Restores the model's planner book to its previous state.
     */
    void undoModulePlanner();

    /**
     * Restores the model's planner book to its previously undone state.
     */
    void redoModulePlanner();

    /**
     * Saves the current planner book state for undo/redo.
     */
    void commitModulePlanner();
}
