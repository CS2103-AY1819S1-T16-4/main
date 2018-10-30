package seedu.planner.model;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {

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

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyModulePlanner newData);

    /** Returns the ModulePlanner */
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
     * Retrieves the actual module information of the {@code modules}
     * and finalizes the modules with their actual module information.
     * Individual modules are finalized using the method
     * {@link ModelManager#finalizeModule(Module) finalizeModule}.
     *
     * @param modules The modules to be finalized
     * @return The modules with their actual module information
     */
    List<Module> finalizeModules(List<Module> modules);

    // @@author

    //@@author GabrielYik

    //TODO: confirm filtered or sorted or both
    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getTakenModuleList(int index);

    //TODO: confirm filtered or sorted or both
    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getAvailableModuleList();

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
