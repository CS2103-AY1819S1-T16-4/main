package seedu.planner.model;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.planner.commons.core.ComponentManager;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.model.ModulePlannerChangedEvent;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;
import seedu.planner.model.user.UserProfile;

/**
 * Represents the in-memory model of the planner book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private UserProfile userProfile;

    private final VersionedModulePlanner versionedModulePlanner;

    private final FilteredList<Module> availableModules;

    //@@author Hilda-Ang

    /**
     * Initializes a ModelManager with the given modulePlanner and userPrefs.
     */
    public ModelManager(ReadOnlyModulePlanner modulePlanner, UserPrefs userPrefs) {
        super();
        requireAllNonNull(modulePlanner, userPrefs);

        logger.fine("Initializing with planner: " + modulePlanner + " and user prefs " + userPrefs);

        versionedModulePlanner = new VersionedModulePlanner(modulePlanner);

        availableModules = new FilteredList<>(versionedModulePlanner.getAvailableModuleList());
    }

    public ModelManager() {
        this(new ModulePlanner(), new UserPrefs());
    }

    //@@author GabrielYik

    @Override
    public void setUpUserProfile(String major, Set<String> focusAreas) {
        versionedModulePlanner.setUserProfile(new UserProfile(major, focusAreas));
        indicateModulePlannerChanged();
    }

    //@@author

    @Override
    public void resetData(ReadOnlyModulePlanner newData) {
        versionedModulePlanner.resetData(newData);
        indicateModulePlannerChanged();
    }

    @Override
    public ReadOnlyModulePlanner getModulePlanner() {
        return versionedModulePlanner;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateModulePlannerChanged() {
        raise(new ModulePlannerChangedEvent(versionedModulePlanner));
    }

    //=========== Module methods =============================================================================

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return versionedModulePlanner.hasModule(module);
    }

    @Override
    public void deleteModules(Set<Module> moduleCodes) {
        versionedModulePlanner.deleteModules(moduleCodes);
        indicateModulePlannerChanged();
    }

    @Override
    public boolean isModuleOffered(Module module) {
        return ModuleInfo.getFromModuleCode(module.getCode()).isPresent();
    }

    /**
     * Retrieves the actual module information of the {@code module}
     * from {@code moduleInfo} and {@code finalizes} that module
     * with the actual module information.
     *
     * @param module The module to be finalized
     * @return The module with the actual module information
     */
    public Module finalizeModule(Module module) {
        Optional<ModuleInfo> optModuleInfo = ModuleInfo.getFromModuleCode(module.getCode());
        if (optModuleInfo.isPresent()) {
            return new Module(ModuleType.PROGRAMME_REQUIREMENTS, optModuleInfo.get());
        }
        return new Module("Unknown");
    }

    @Override
    public Set<Module> finalizeModules(Set<Module> modules) {
        Set<Module> finalizedModules = new HashSet<>();
        for (Module m : modules) {
            finalizedModules.add(finalizeModule(m));
        }
        return finalizedModules;
    }

    //@@author RomaRomama

    @Override
    public void addModules(Set<Module> modules, int index) {
        Set<Module> finalizedModules = finalizeModules(modules);
        versionedModulePlanner.addModules(finalizedModules, index);
        indicateModulePlannerChanged();
    }

    //@@author

    //=========== Filtered Module List Accessors =============================================================
    //@@author GabrielYik

    @Override
    public ObservableList<Module> getTakenModuleList(int index) {
        return FXCollections.unmodifiableObservableList(
                versionedModulePlanner.getModulesTaken(index));
    }

    @Override
    public ObservableList<Module> getAvailableModuleList() {
        return FXCollections.unmodifiableObservableList(availableModules);
    }

    //@@author

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoModulePlanner() {
        return versionedModulePlanner.canUndo();
    }

    @Override
    public boolean canRedoModulePlanner() {
        return versionedModulePlanner.canRedo();
    }

    @Override
    public void undoModulePlanner() {
        versionedModulePlanner.undo();
        indicateModulePlannerChanged();
    }

    @Override
    public void redoModulePlanner() {
        versionedModulePlanner.redo();
        indicateModulePlannerChanged();
    }

    @Override
    public void commitModulePlanner() {
        versionedModulePlanner.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedModulePlanner.equals(other.versionedModulePlanner);
    }
}
