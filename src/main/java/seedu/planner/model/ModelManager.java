package seedu.planner.model;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.planner.commons.core.ComponentManager;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.model.AddressBookChangedEvent;
import seedu.planner.commons.events.model.ModulePlannerChangedEvent;
import seedu.planner.model.enumeration.FocusArea;
import seedu.planner.model.enumeration.Major;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;
import seedu.planner.model.person.Person;
import seedu.planner.model.util.SampleModulePlannerUtil;

/**
 * Represents the in-memory model of the planner book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    // TODO: Delete this
    private final VersionedAddressBook versionedAddressBook;
    private final FilteredList<Person> filteredPersons;

    private final ModuleInfo[] moduleInfo;

    private UserProfile userProfile;

    private final VersionedModulePlanner versionedModulePlanner;

    // TODO: Delete this
    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        this(addressBook, new ModuleInfo[] {}, userPrefs);
    }

    public ModelManager(ReadOnlyAddressBook addressBook, ModuleInfo[] moduleInfo, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with planner book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());

        this.moduleInfo = moduleInfo;
        versionedModulePlanner = new VersionedModulePlanner(
                SampleModulePlannerUtil.genModulePlanner(new ModulePlanner()));
    }

    //@@author Hilda-Ang

    /**
     * Initializes a ModelManager with the given modulePlanner and userPrefs.
     */
    public ModelManager(ReadOnlyModulePlanner modulePlanner, ModuleInfo[] moduleInfo, UserPrefs userPrefs) {
        super();
        requireAllNonNull(modulePlanner, userPrefs);

        logger.fine("Initializing with planner: " + modulePlanner + " and user prefs " + userPrefs);

        versionedModulePlanner = new VersionedModulePlanner(modulePlanner);
        this.moduleInfo = moduleInfo;

        //TODO: Delete this
        this.versionedAddressBook = new VersionedAddressBook(new AddressBook());
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());
    }

    public ModelManager() {
        this(new ModulePlanner(), new ModuleInfo[]{}, new UserPrefs());
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
    private boolean hasFocusArea(String focusArea) {
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
    public void resetData(ReadOnlyAddressBook newData) {
        versionedAddressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(versionedAddressBook));
    }

    private void indicateModulePlannerChanged() {
        raise(new ModulePlannerChangedEvent(versionedModulePlanner));
    }

    //=========== Person methods =============================================================================

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

    @Override
    public void updatePerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedAddressBook.updatePerson(target, editedPerson);
        indicateAddressBookChanged();
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
        for (ModuleInfo mi : moduleInfo) {
            if (mi.getCode().equals(module.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the actual module information of the {@code module}
     * from {@code moduleInfo} and {@code finalizes} that module
     * with the actual module information.
     *
     * @param module The module to be finalized
     * @return The module with the actual module information
     */
    private Module finalizeModule(Module module) {
        for (ModuleInfo mi : moduleInfo) {
            if (mi.getCode().equals(module.getCode())) {
                return new Module(ModuleType.PROGRAMME_REQUIREMENTS, mi);
            }
        }
        return new Module("Unknown");
    }

    /**
     * Retrieves the actual module information of the {@code modules}
     * and finalizes the modules with their actual module information.
     * Individual modules are finalized using the method
     * {@link #finalizeModule(Module) finalizeModule}.
     *
     * @param modules The modules to be finalized
     * @return The modules with their actual module information
     */
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

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredPersons);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    // @@author rongjiecomputer
    public ModuleInfo[] getModuleInfo() {
        return moduleInfo;
    }
    // @@author

    //=========== Filtered Module List Accessors =============================================================
    //@@author GabrielYik

    @Override
    public ObservableList<Module> getFilteredTakenModuleList(int index) {
        return FXCollections.unmodifiableObservableList(
                versionedModulePlanner.listModulesTaken(index));
    }

    @Override
    public ObservableList<Module> getFilteredAvailableModuleList(int index) {
        return FXCollections.unmodifiableObservableList(
                versionedModulePlanner.listModulesAvailable(index));
    }

    //@@author

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
        indicateAddressBookChanged();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
        indicateAddressBookChanged();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
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
        return versionedAddressBook.equals(other.versionedAddressBook)
                && filteredPersons.equals(other.filteredPersons);
    }
}
