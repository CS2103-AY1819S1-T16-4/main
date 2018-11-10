package seedu.planner.model;

import java.util.Set;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.planner.model.course.DegreeRequirement;
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

    ObservableMap<DegreeRequirement, int[]> getStatus();

    //@@author Hilda-Ang
    /**
     * Displays modules that are available to the user in the specified index, in the list of suggested modules.
     *
     * @param index An integer between 0 to 7 inclusive, signifying year and semester to be suggested.
     */
    void suggestModules(int index);

    /**
     * Displays all modules that user has added in every semester.
     */
    void listTakenModulesAll();

    /**
     * Displays all modules that the user has added to a specified year.
     *
     * @param year A valid integer between 1 to 4 inclusive, signifying year to be listed.
     */
    void listTakenModulesYear(int year);

    /**
     * Retrieves a list containing modules that have been taken (added to ModulePlanner) by the user.
     *
     * @return An unmodifiable view of modules taken by the user.
     */
    ObservableList<Module> listTakenModules();

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

    /**
     * Retrieves an unmodifiable view of the modules taken for (added to) a specified index.
     *
     * @param index An integer between 0 to 7 inclusive.
     * @return An unmodifiable list of modules taken for index.
     */
    ObservableList<Module> getTakenModulesForIndex(int index);

    /**
     * Retrieves an unmodifiable view of the modules taken for (added to) a specified index.
     *
     * @return An unmodifiable list of all modules that the user is available to take.
     */
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
