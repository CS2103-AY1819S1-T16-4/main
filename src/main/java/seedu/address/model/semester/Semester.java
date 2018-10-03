package seedu.address.model.semester;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.Module;

//@@author Hilda-Ang

/**
 * Represents a Semester in the module planner.
 * Holds list of modules taken and list of modules suggested for a particular semester.
 */
public class Semester {

    // Basic information
    private final int semester;
    private final int year;

    // Indicator of whether user has passed the semester
    private boolean past;

    // Modules lists
    private List<Module> modulesTaken = new ArrayList<Module>();
    private List<Module> modulesSuggested = new ArrayList<Module>();

    /**
     * Constructs a {@code Semester}.
     *
     * @param semester A valid semester.
     * @param year A valid year.
     * @param past An indicator of whether the semester has been passed.
     */
    public Semester(int semester, int year, boolean past) {
        this.semester = semester;
        this.year = year;
        this.past = past;
    }

    /**
     * Adds one or more module(s) to list of modules taken.
     *
     * @param modules A non-empty list of modules to be added.
     */
    public void addModules(List<Module> modules) {

    }

    /**
     * Deletes one or more module(s) from list of modules taken if present.
     *
     * @param modules A non-empty list of modules to be deleted.
     */
    public void deleteModules(List<Module> modules) {

    }

    /**
     * Returns list of modules taken in a particular semester.
     *
     * @return A list of modules taken.
     */
    public List<Module> listModulesTaken() {
        return modulesTaken;
    }

    /**
     * Returns list of modules that the user is available to take in a particular semester.
     *
     * @return A list of suggested modules.
     */
    public List<Module> listModulesSuggested() {
        return modulesSuggested;
    }
}
