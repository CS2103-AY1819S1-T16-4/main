package seedu.planner.model.semester;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;

//@@author Hilda-Ang

/**
 * Represents a Semester in the module planner.
 * Holds the list of modules taken and the list of modules available for
 * that semester.
 *
 * A semester can be characterised by the year it is in, and whether it is
 * the first or second semester in that year.
 */
public class Semester {

    /** Constant for the first index. */
    public static final int FIRST = 1;

    /** Constant for the second index. */
    public static final int SECOND = 2;

    // Basic information
    private final int index;
    private final int year;

    // Indicator of whether user has passed the index
    private boolean hasBeenTaken;

    // Modules lists
    private ObservableList<Module> modulesTaken = FXCollections.observableArrayList();
    private ObservableList<Module> modulesAvailable = FXCollections.observableArrayList();

    /**
     * Constructs a {@code Semester}.
     *
     * @param year A valid year
     * @param index A valid index
     * @param hasBeenTaken Whether the semester has been taken
     */
    public Semester(int year, int index, boolean hasBeenTaken) {
        this.year = year;
        this.index = index;
        this.hasBeenTaken = hasBeenTaken;
    }

    public int getIndex() {
        return index;
    }

    public int getYear() {
        return year;
    }

    /**
     * Adds one or more module(s) to the list of modules taken.
     *
     * @param modules A non-empty list of modules to be added
     */
    public void addModules(List<Module> modules) {
        modulesTaken.addAll(modules);
    }

    /**
     * Adds one or more modules(s) to the list of modules available.
     *
     * @param modules A non-empty list of modules to be added
     */
    public void addAvailableModules(List<Module> modules) {
        modulesAvailable.addAll(modules);
    }

    /**
     * Deletes one or more module(s) from list of modules taken if present.
     *
     * @param modules A non-empty list of modules to be deleted
     */
    public void deleteModules(List<Module> modules) {
        for (Module m : modules) {
            modulesTaken.remove(m);
        }
    }

    /**
     * Checks if the module with is taken or planned to take in the semester.
     *
     * @param module The module
     * @return True if the module exists, false if not
     */
    public boolean containsModule(Module module) {
        requireNonNull(module);
        return modulesTaken.stream().anyMatch(module::equals);
    }

    /**
     * Changes the {@code Semester}'s status from currently being taken
     * to taken.
     */
    public void archive() {
        this.hasBeenTaken = true;
    }

    /**
     * Returns the list of modules taken in this semester.
     *
     * @return A list of modules taken
     */
    public ObservableList<Module> getModulesTaken() {
        return modulesTaken;
    }

    /**
     * Returns a list of modules that the user is available to take
     * in this semester.
     *
     * @return A list of available modules
     */
    public ObservableList<Module> getModulesAvailable() {
        return modulesAvailable;
    }
}
