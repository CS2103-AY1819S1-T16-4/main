package seedu.planner.model;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Sets up the user profile.
     *
     * @param major The major
     * @param focusAreas The focus areas
     */
    void setUpUserProfile(String major, Set<String> focusAreas);

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
    void deleteModules(Set<Module> modules);

    /**
     * Add set of modules into the specified semester
     *
     * @param modules Set of modules
     * @param index Index of the semester
     */
    void addModules(Set<Module> modules, int index);

    //TODO: hilda
    void suggestModules(int index);

    //TODO: hilda
    void listTakenModulesAll();

    //TODO: hilda
    void listTakenModulesYear(int year);

    //TODO: hilda
    ObservableList<Module> listModules();

    /**
     * Retrieves the actual module information of the {@code modules}
     * and finalizes the modules with their actual module information.
     * Individual modules are finalized using the method
     * {@link ModelManager#finalizeModule(Module) finalizeModule}.
     *
     * @param modules The modules to be finalized
     * @return The modules with their actual module information
     */
    Set<Module> finalizeModules(Set<Module> modules);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getTakenModules(int index);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getAvailableModules();

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
