package seedu.planner.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;
import seedu.planner.model.semester.Semester;

//@@author Hilda-Ang //@@author GabrielYik

/**
 * Wraps all data at the module planner level.
 */
public class ModulePlanner implements ReadOnlyModulePlanner {

    public static final int MAX_NUMBER_SEMESTERS = 8;

    /**
     * The number of {@code Module} groups that is shown to the user.
     * Currently, there are two groups: one for modules taken and
     * one for modules available. A {@code Module} group is different
     * from a {@code ModuleType}.
     */
    public static final int NUMBER_MODULE_GROUPS = 2;

    private final List<Semester> semesters = new ArrayList<>(MAX_NUMBER_SEMESTERS);

    public ModulePlanner() {

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
     * @param semesterIndex A valid semester
     */
    public void addModules(List<Module> modules, int semesterIndex) {
        semesters.get(semesterIndex).addModules(modules);
    }

    /**
     * Delete one or more module(s) from list of modules taken for the specified semester.
     *
     * @param modules A list of valid modules to be deleted
     * @param semesterIndex A valid semester
     */
    public void deleteModules(List<Module> modules, int semesterIndex) {
        semesters.get(semesterIndex).deleteModules(modules);
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
     * @param semesterIndex The nominal {@code Semester} index the {@code Module}s
     *                      are stored at
     * @return An {@code ObservableList} containing all the {@code Module}s
     */
    @Override
    public ObservableList<Module> getModulesTaken(int semesterIndex) {
        List<Module> modules = semesters.get(semesterIndex).getModulesTaken();
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableList(modules));
    }


    //TODO: available modules might not be placed by year
    /**
     * Returns all {@code Module}s available in the {@code Semester} wrapped in an
     * {@code ObservableList}.
     *
     * @param semesterIndex The nominal {@code Semester} index the {@code Module}s
     *                      are stored at
     * @return An {@code ObservableList} containing all the {@code Module}s
     */
    @Override
    public ObservableList<Module> getModulesAvailable(int semesterIndex) {
        List<Module> modules = semesters.get(semesterIndex).getModulesAvailable();
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableList(modules));
    }

    /**
     * Resets the existing data of this {@code ModulePlanner} with {@code newData}.
     */
    public void resetData(ReadOnlyModulePlanner newData) {
        requireNonNull(newData);
        setSemesters(newData.getSemesters());
    }

    //TODO: change to private once SampleModuleUtil is not needed, and remove the toRemove boolean
    public void setSemesters(List<Semester> semesters) {
        boolean toRemove = false;
        if (this.semesters.size() != 0) {
            toRemove = true;
        }
        for (int i = 0; i < MAX_NUMBER_SEMESTERS; i++) {
            if (toRemove) {
                this.semesters.remove(i);
            }
            this.semesters.add(semesters.get(i));
        }
    }
}
