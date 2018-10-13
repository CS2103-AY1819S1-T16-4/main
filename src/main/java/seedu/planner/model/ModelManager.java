package seedu.planner.model;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.planner.model.ModulePlanner.MAX_NUMBER_SEMESTERS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.planner.commons.core.ComponentManager;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.model.AddressBookChangedEvent;
import seedu.planner.model.module.Module;
import seedu.planner.model.person.Person;
import seedu.planner.model.util.SampleModulePlannerUtil;

/**
 * Represents the in-memory model of the planner book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final FilteredList<Person> filteredPersons;

    private final VersionedModulePlanner versionedModulePlanner;

    /**
     * Represents a {@code FilteredList<Module>} of taken {@code Module}s
     * ordered in ascending order in terms of their respective {@code Semester}s.
     */
    private final List<FilteredList<Module>> filteredTakenModules;

    /**
     * Represents a {@code FilteredList<Module>} of available {@code Module}s
     * ordered in ascending order in terms of their respective {@code Semester}s.
     */
    private final List<FilteredList<Module>> filteredAvailableModules;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with planner book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());

        //@@author GabrielYik

        //TODO: initialise ModulePlanner properly
        ModulePlanner modulePlanner = SampleModulePlannerUtil.genModulePlanner();
        versionedModulePlanner = new VersionedModulePlanner(modulePlanner);
        filteredTakenModules = setUpFilteredTakenModules();
        filteredAvailableModules = setUpFilteredAvailableModules();

        //@@author
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //@@author GabrielYik

    private List<FilteredList<Module>> setUpFilteredTakenModules() {
        List<FilteredList<Module>> filteredTakenModules = new ArrayList<>(MAX_NUMBER_SEMESTERS);
        for (int i = 0; i < MAX_NUMBER_SEMESTERS; i++) {
            filteredTakenModules.add(i, new FilteredList<>(
                    versionedModulePlanner.getModulesTaken(i)));
        }
        return filteredTakenModules;
    }

    private List<FilteredList<Module>> setUpFilteredAvailableModules() {
        List<FilteredList<Module>> filteredAvailableModules = new ArrayList<>(MAX_NUMBER_SEMESTERS);
        for (int i = 0; i < MAX_NUMBER_SEMESTERS; i++) {
            filteredAvailableModules.add(i, new FilteredList<>(
                    versionedModulePlanner.getModulesAvailable(i)));
        }
        return filteredAvailableModules;
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

    //=========== Filtered Module List Accessors =============================================================
    //@@author GabrielYik

    @Override
    public ObservableList<Module> getFilteredTakenModuleList(int semesterIndex) {
        return FXCollections.unmodifiableObservableList(
                filteredTakenModules.get(semesterIndex));
    }

    @Override
    public ObservableList<Module> getFilteredAvailableModuleList(int semesterIndex) {
        return FXCollections.unmodifiableObservableList(
                filteredAvailableModules.get(semesterIndex));
    }

    @Override
    public void updateFilteredTakenModuleList(Predicate<Module> predicate, int semesterIndex) {
        requireNonNull(predicate);
        filteredTakenModules.get(semesterIndex).setPredicate(predicate);
    }

    @Override
    public void updateFilteredAvailableModuleList(Predicate<Module> predicate, int semesterIndex) {
        requireNonNull(predicate);
        filteredAvailableModules.get(semesterIndex).setPredicate(predicate);
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
