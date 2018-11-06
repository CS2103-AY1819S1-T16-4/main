package seedu.planner.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.semester.Semester;
import seedu.planner.model.user.UserProfile;
import seedu.planner.model.util.IndexUtil;
import seedu.planner.model.util.ModuleUtil;

//@@author Hilda-Ang

/**
 * Wraps all data at the module planner level.
 */
public class ModulePlanner implements ReadOnlyModulePlanner {

    public static final int MAX_NUMBER_SEMESTERS = 8;
    public static final int MAX_SEMESTERS_PER_YEAR = 2;

    private final List<Semester> semesters;
    private UserProfile userProfile;

    private final ObservableList<Module> availableModules = FXCollections.observableArrayList();
    private final ObservableList<Module> takenModules = FXCollections.observableArrayList();

    private int index;

    /**
     * Constructs a {@code ModulePlanner} and initializes an array of 8 {@code Semester}
     * to store details of each {@code Semester}.
     */
    public ModulePlanner() {
        semesters = new ArrayList<>(MAX_NUMBER_SEMESTERS);
        userProfile = new UserProfile();

        for (int i = 1; i <= MAX_NUMBER_SEMESTERS / MAX_SEMESTERS_PER_YEAR; i++) {
            for (int j = 1; j <= MAX_SEMESTERS_PER_YEAR; j++) {
                semesters.add(new Semester(i, j));
            }
        }

        index = 0;
    }

    /**
     * Creates a {@code ModulePlanner} using the {@code Module}s in the {@code toBeCopied}
     */
    public ModulePlanner(ReadOnlyModulePlanner toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setAvailableModules(List<Module> modules) {
        availableModules.setAll(modules);
    }

    /**
     * Add one or more module(s) to set of modules taken for the specified semester.
     *
     * @param modules A set of valid modules to be added
     * @param index A valid semester
     */
    public void addModules(Set<Module> modules, int index) {
        semesters.get(index).addModules(modules);
        setAvailableModules(getModulesAvailable(this.index));
    }

    //@@author GabrielYik

    /**
     * Delete one or more module(s) from list of modules taken for the specified semester.
     *
     * @param modules A list of valid modules to be deleted
     */
    public void deleteModules(Set<Module> modules) {
        // Iterate once to delete the specified modules
        for (Semester semester : semesters) {
            semester.deleteModules(modules);
        }

        /*
         * Delete invalidated modules repeatedly until there are no more changes
         * to the total number of modules in the module planner
         */
        Set<Module> deletedModules = new HashSet<>(modules);
        int previousModuleCount = countModules();
        while (true) {
            for (int index = 0; index < MAX_NUMBER_SEMESTERS; index++) {
                Semester semester = semesters.get(index);
                List<Module> invalidatedModules = new ArrayList<>();
                invalidateModules(semester, deletedModules, invalidatedModules);
                deleteInvalidatedModules(semester, deletedModules, invalidatedModules);
            }

            int currentModuleCount = countModules();
            if (currentModuleCount == previousModuleCount) {
                break;
            }
            if (currentModuleCount < previousModuleCount) {
                previousModuleCount = currentModuleCount;
            }
        }

        setAvailableModules(getModulesAvailable(index));
    }

    /**
     * Counts the number of modules in the module planner.
     *
     * @return The total number of modules
     */
    private int countModules() {
        int count = 0;
        for (Semester semester : semesters) {
            count += semester.getModules().size();
        }
        return count;
    }

    /**
     * Checks if all the modules in {@code semester} have their prerequisites
     * fulfilled. If any of the modules do not, they will be added to
     * {@code invalidatedModules}.
     *
     * @param semester The semester which modules are to be checked
     * @param deletedModules The modules to be checked against
     * @param invalidatedModules The group of modules any of the modules in
     *  {@code semester} will be added to if it does not fulfill all of
     *  it's prerequisites
     */
    private void invalidateModules(Semester semester, Set<Module> deletedModules, List<Module> invalidatedModules) {
        for (Module module : semester.getModules()) {
            List<ModuleInfo> prerequisites = module.getPrerequisites();
            if (!prerequisites.isEmpty()) {
                boolean hasHadPrerequisiteDeleted = prerequisites.stream().anyMatch(x ->
                        deletedModules.stream().anyMatch(y -> x.getCode().equals(y.getCode())));
                if (hasHadPrerequisiteDeleted) {
                    invalidatedModules.add(module);
                }
            }
        }
    }

    /**
     * Deletes the {@code invalidatedModules} from the modules {@code semester} has.
     *
     * @param semester The semester which the {@code invalidatedModules} are to be
     *  deleted from
     * @param invalidatedModules The invalidated modules
     */
    private void deleteInvalidatedModules(Semester semester, Set<Module> deletedModules,
            List<Module> invalidatedModules) {
        if (!invalidatedModules.isEmpty()) {
            Set<Module> modulesToDelete = new HashSet<>(invalidatedModules);
            semester.deleteModules(modulesToDelete);
            deletedModules.addAll(modulesToDelete);
        }
    }

    //@@author Hilda-Ang

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

    @Override
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile u) {
        userProfile = u;
    }

    /**
     * Returns a copy of all the {@code Semester}s.
     *
     * @return A list of {@code Semester}s
     */
    @Override
    public List<Semester> getSemesters() {
        List<Semester> semestersCopy = new ArrayList<>();
        for (Semester semester : semesters) {
            Semester semesterCopy = new Semester(semester);
            semestersCopy.add(semesterCopy);
        }
        return semestersCopy;
    }

    /**
     * Returns all {@code Module}s taken in the {@code Semester} wrapped in an
     * {@code ObservableList}.
     *
     * @param index A valid index.
     * @return A list of modules taken in the semester.
     */
    @Override
    public ObservableList<Module> getTakenModules(int index) {
        return FXCollections.unmodifiableObservableList(
                semesters.get(index).getModules());
    }

    /**
     * Updates {@code modulesTaken} to contain all modules user has taken for every semester.
     */
    public void listTakenModulesAll() {
        List<Module> modules = new ArrayList<>();
        for (Semester s: semesters) {
            modules.addAll(s.getModules());
        }
        setTakenModules(modules);
    }

    /**
     * Updates {@code modulesTaken} to contain all modules user has taken in a given year.
     *
     * @param year An integer between 1 to 4.
     */
    public void listTakenModulesYear(int year) {
        int[] indices = IndexUtil.getIndicesFromYear(year);
        List<Module> modules = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            modules.addAll(semesters.get(indices[i]).getModules());
        }
        setTakenModules(modules);
    }

    public ObservableList<Module> listTakenModules() {
        return takenModules;
    }

    private void setTakenModules(List<Module> modules) {
        takenModules.setAll(modules);
    }

    /**
     * Returns all {@code Module}s available wrapped in an {@code ObservableList}.
     *
     * @return An {@code ObservableList} containing all the {@code Module}s
     */
    public ObservableList<Module> getAvailableModules() {
        setAvailableModules(getModulesAvailable(index));
        return availableModules;
    }

    /**
     * Resets the existing data of this {@code ModulePlanner} with {@code newData}.
     */
    public void resetData(ReadOnlyModulePlanner newData) {
        requireNonNull(newData);
        setAvailableModules(getModulesAvailable(index));
        setModulesInSemesters(newData.getSemesters());
    }


    public void setModulesInSemesters(List<Semester> semesters) {
        for (int i = 0; i < MAX_NUMBER_SEMESTERS; i++) {
            this.semesters.get(i).setTakenModules(semesters.get(i));
        }
    }

    /**
     * Updates the list of modules available based on given index and stores the index for add and delete commands.
     *
     * @param index An integer from 0 to 7 inclusive indicating the year and semester to suggest.
     */
    public void suggestModules(int index) {
        this.index = index;
        setAvailableModules(getModulesAvailable(index));
    }

    /**
     * Get a list of all the modules user can take based on the modules user has taken until given index.
     *
     * @param index An integer from 0 to 7 inclusive to show the current year and semester to suggest.
     * @return A list of {@code Module}s the user is available to take.
     */
    private List<Module> getModulesAvailable(int index) {
        List<Module> modulesAvailable = new ArrayList<>();
        List<Module> modulesTaken = getAllModulesTaken();
        List<Module> modulesTakenBeforeIndex = getAllModulesTakenBeforeIndex(index);
        List<Module> allModules = getAllModulesFromStorage();

        for (Module m: allModules) {
            if (ModuleUtil.isModuleAvailableToTake(modulesTaken, modulesTakenBeforeIndex, m)) {
                modulesAvailable.add(m);
            }
        }
        return modulesAvailable;
    }

    /**
     * Combines the list of {@code Module}s taken from every {@code Semester}.
     *
     * @return A list of all {@code Module}s the user has taken.
     */
    private List<Module> getAllModulesTaken() {
        List<Module> modulesTaken = new ArrayList<>();
        for (Semester s: semesters) {
            modulesTaken.addAll(s.getModules());
        }
        return modulesTaken;
    }

    /**
     * Combines the list of {@code Module}s taken for every {@code Semester} until current index.
     *
     * @param index The current index user is at.
     * @return A list of all {@code Module}s the user has taken until the specified index.
     */
    private List<Module> getAllModulesTakenBeforeIndex(int index) {
        List<Module> modulesTaken = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            modulesTaken.addAll(semesters.get(i).getModules());
        }
        return modulesTaken;
    }

    /**
     * Get a list of all {@code Module}s data stored.
     *
     * @return A list of all {@code Module}s in the storage.
     */
    private List<Module> getAllModulesFromStorage() {
        ModuleInfo[] allModuleInfo = ModuleInfo.getModuleInfoList();
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
