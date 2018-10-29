package seedu.planner.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.semester.Semester;
import seedu.planner.model.util.ModuleUtil;

//@@author Hilda-Ang //@@author GabrielYik

/**
 * Wraps all data at the module planner level.
 */
public class ModulePlanner implements ReadOnlyModulePlanner {

    public static final int MAX_NUMBER_SEMESTERS = 8;
    public static final int MAX_SEMESTERS_PER_YEAR = 2;

    /**
     * The number of {@code Module} groups that is shown to the user.
     * Currently, there are two groups: one for modules taken and
     * one for modules available. A {@code Module} group is different
     * from a {@code ModuleType}.
     */
    public static final int NUMBER_MODULE_GROUPS = 2;

    private final List<Semester> semesters;

    /**
     * Constructs a {@code ModulePlanner} and initializes an array of 8 {@code Semester}
     * to store details of each {@code Semester}.
     */
    public ModulePlanner() {
        semesters = new ArrayList<>(MAX_NUMBER_SEMESTERS);

        for (int i = 1; i <= MAX_NUMBER_SEMESTERS / MAX_SEMESTERS_PER_YEAR; i++) {
            for (int j = 1; j <= MAX_SEMESTERS_PER_YEAR; j++) {
                semesters.add(new Semester(i, j));
            }
        }
    }

    /**
     * Creates a {@code ModulePlanner} using the {@code Module}s in the {@code toBeCopied}
     */
    public ModulePlanner(ReadOnlyModulePlanner toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Add one or more module(s) to list of modules taken for the specified semester.
     *
     * @param modules A list of valid modules to be added
     * @param index A valid semester
     */
    public void addModules(List<Module> modules, int index) {
        semesters.get(index).addModules(modules);
    }

    /**
     * Delete one or more module(s) from list of modules taken for the specified semester.
     *
     * @param modules A list of valid modules to be deleted
     */
    public void deleteModules(List<Module> modules) {
        for (Semester semester : semesters) {
            semester.deleteModules(modules);
        }
    }

    /**
     * Checks if the {@code Module} exists in the module planner.
     *
     * @param module The module to check
     * @return True if the module exists, false if not
     */
    public boolean hasModule(Module module) {
        for (Semester semester : semesters) {
            if (semester.containsModule(module)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all {@code Semester}s wrapped in an {@code ObservableList}.
     *
     * @return An {@code ObservableList} containing all the {@code Semester}s
     */
    @Override
    public ObservableList<Semester> getSemesters() {
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableList(semesters));
    }

    /**
     * Returns all {@code Module}s taken in the {@code Semester} wrapped in an
     * {@code ObservableList}.
     *
     * @param index A valid index.
     * @return A list of modules taken in the semester.
     */
    @Override
    public ObservableList<Module> getModulesTaken(int index) {
        return FXCollections.unmodifiableObservableList(
                semesters.get(index).getModulesTaken());
    }

    /**
     * Returns all {@code Module}s available wrapped in an {@code ObservableList}.
     *
     * @return An {@code ObservableList} containing all the {@code Module}s
     */
    @Override
    public ObservableList<Module> getModulesAvailable() {
        List<Module> modulesAvailable = new ArrayList<>();
        List<Module> modulesTaken = this.getAllModulesTaken();
        List<Module> allModules = this.getAllModulesFromStorage();

        for (Module m: allModules) {
            if (ModuleUtil.isModuleAvailableToTake(modulesTaken, m)) {
                modulesAvailable.add(m);
            }
        }

        return FXCollections.observableList(modulesAvailable);
    }

    /**
     * Resets the existing data of this {@code ModulePlanner} with {@code newData}.
     */
    public void resetData(ReadOnlyModulePlanner newData) {
        requireNonNull(newData);
        setSemesters(newData.getSemesters());
    }

    public void setSemesters(List<Semester> semesters) {
        for (int i = 0; i < MAX_NUMBER_SEMESTERS; i++) {
            this.semesters.remove(i);
            this.semesters.add(i, semesters.get(i));
        }
    }

    /**
     * Combines the list of {@code Module}s taken from every {@code Semester}.
     *
     * @return A list of all {@code Module}s the user has taken.
     */
    private List<Module> getAllModulesTaken() {
        List<Module> modulesTaken = new ArrayList<>();
        for (Semester s: semesters) {
            modulesTaken.addAll(s.getModulesTaken());
        }
        return modulesTaken;
    }

    /**
     * Get a list of all {@code Module}s data stored.
     *
     * @return A list of all {@code Module}s in the storage.
     */
    private List<Module> getAllModulesFromStorage() {
        ModuleInfo[] allModuleInfo = ModuleInfo.ModuleInfoRetriever.getInstance().getModuleInfoList();
        List<Module> allModules = new ArrayList<>();

        for (ModuleInfo mi: allModuleInfo) {
            Module m = new Module(mi.getCode());
            allModules.add(m);
        }
        return allModules;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModulePlanner // instanceof handles nulls
                && semesters.equals(((ModulePlanner) other).semesters));
    }

    @Override
    public int hashCode() {
        return semesters.hashCode();
    }
}
