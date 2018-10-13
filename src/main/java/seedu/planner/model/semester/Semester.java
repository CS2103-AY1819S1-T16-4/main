package seedu.planner.model.semester;

import java.util.ArrayList;
import java.util.List;

import seedu.planner.model.module.Module;

//@@author Hilda-Ang

/**
 * Represents a Semester in the module planner.
 * Holds the list of modules taken and the list of modules available for
 * that semester.
 *
 * <p> A semester can be characterised by the year it is in, and whether it is
 *  * the first or second semester in that year. The first or second label
 *  for the semester can be defined as its offset index. However, for convenience,
 *  semesters may be addressed by their nominal index. The range of the
 *  nominal index is from 0 to 8 exclusive.
 *
 * <h2>Example</h2>
 *
 * <ul>
 *
 *  <li> A semester in year 1 semester 1 would have a nominal index of 0
 *      and an offset index of 1
 *
 *  <li> A semester in year 1 semester 2 would have a nominal index of 1
 *       and an offset index of 2
 *
 *  <li> A semester in year 4 semester 2 would have a nominal index of 7
 *       and an offset index of 2
 *
 * </ul>
 */
public class Semester {

    /** Constant for offset index 1. */
    public static final int FIRST = 1;

    /** Constant for offset index 2. */
    public static final int SECOND = 2;

    // Basic information
    private final int index;
    private final int year;

    // Indicator of whether user has passed the index
    private boolean hasBeenTaken;

    // Modules lists
    private List<Module> modulesTaken = new ArrayList<Module>();
    private List<Module> modulesAvailable = new ArrayList<Module>();

    /**
     * Constructs a {@code Semester}.
     *
     * @param index A valid offset index
     * @param year A valid year
     * @param hasBeenTaken An indicator of whether the semester
     *                  has been taken
     */
    public Semester(int index, int year, boolean hasBeenTaken) {
        this.index = index;
        this.year = year;
        this.hasBeenTaken = hasBeenTaken;
    }

    //TODO: change dummy implementation
    /**
     * Adds one or more module(s) to the list of modules taken.
     *
     * @param modules A non-empty list of modules to be added
     */
    public void addModules(List<Module> modules) {
        this.modulesTaken = modules;
    }

    //TODO: change dummy implementation
    /**
     * Adds one or more modules(s) to the list of modules available.
     *
     * @param modules A non-empty list of modules to be added
     */
    public void addAvailableModules(List<Module> modules) {
        this.modulesAvailable = modules;
    }

    /**
     * Deletes one or more module(s) from list of modules taken if present.
     *
     * @param modules A non-empty list of modules to be deleted
     */
    public void deleteModules(List<Module> modules) {

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
    public List<Module> getModulesTaken() {
        return modulesTaken;
    }

    /**
     * Returns a list of modules that the user is available to take
     * in this semester.
     *
     * @return A list of available modules
     */
    public List<Module> getModulesAvailable() {
        return modulesAvailable;
    }
}
