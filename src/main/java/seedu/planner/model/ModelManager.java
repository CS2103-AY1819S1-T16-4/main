package seedu.planner.model;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.planner.commons.core.ComponentManager;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.model.ModulePlannerChangedEvent;
import seedu.planner.model.course.FocusArea;
import seedu.planner.model.course.Major;
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

    //@@author Hilda-Ang

    /**
     * Initializes a ModelManager with the given modulePlanner and userPrefs.
     */
    public ModelManager(ReadOnlyModulePlanner modulePlanner, UserPrefs userPrefs) {
        super();
        requireAllNonNull(modulePlanner, userPrefs);

        logger.fine("Initializing with planner: " + modulePlanner + " and user prefs " + userPrefs);

        versionedModulePlanner = new VersionedModulePlanner(modulePlanner);
    }

    public ModelManager() {
        this(new ModulePlanner(), new UserPrefs());
    }

    @Override
    public void setUpUserProfile(int year, int semester, String major, Set<String> focusAreas) {
        UserProfile.setUp(year, semester, major, focusAreas);
        userProfile = UserProfile.getInstance();
    }

    @Override
    public boolean hasMajor(String major) {
        for (Major m : Major.values()) {
            if (m.toString().equals(major)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the {@code focusArea} is valid.
     * The focus area is checked against a list of available
     * focus areas offered by the relevant educational institution.
     *
     * @param focusArea The focus area
     * @return True if the focus area is offered, else false
     */
    public boolean hasFocusArea(String focusArea) {
        for (FocusArea fa : FocusArea.values()) {
            if (fa.toString().equals(focusArea)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasFocusAreas(Set<String> focusArea) {
        for (String fa : focusArea) {
            if (!hasFocusArea(fa)) {
                return false;
            }
        }
        return true;
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
    public void deleteModules(List<Module> moduleCodes) {
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
    public List<Module> finalizeModules(List<Module> modules) {
        List<Module> finalizedModules = new ArrayList<>();
        for (Module m : modules) {
            finalizedModules.add(finalizeModule(m));
        }
        return finalizedModules;
    }

    //@@author RomaRomama

    @Override
    public void addModules(List<Module> modules, int index) {
        List<Module> finalizedModules = finalizeModules(modules);
        versionedModulePlanner.addModules(finalizedModules, index);
        indicateModulePlannerChanged();
    }

    //@@author

    // @@author rongjiecomputer

    /**
     * Get a list of ModuleInfo from JSON file.
     *
     * @deprecated Use ModuleInfo.getModuleInfoList() directly instead.
     */
    public ModuleInfo[] getModuleInfo() {
        return ModuleInfo.getModuleInfoList();
    }
    // @@author

    //=========== Filtered Module List Accessors =============================================================
    //@@author GabrielYik

    @Override
    public ObservableList<Module> getTakenModuleList(int index) {
        return FXCollections.unmodifiableObservableList(
                versionedModulePlanner.listModulesTaken(index));
    }

    @Override
    public ObservableList<Module> getAvailableModuleList(int index) {
        return FXCollections.unmodifiableObservableList(
                versionedModulePlanner.listModulesAvailable(index));
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
