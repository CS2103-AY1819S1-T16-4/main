package seedu.planner.model;

import java.util.ArrayList;
import java.util.List;

import seedu.planner.model.module.Module;
import seedu.planner.model.semester.Semester;

//@@author Hilda-Ang

/**
 * Wraps all data at the module planner level, holds 8 semesters.
 */
public class ModulePlanner {

    private final int maxSemesters = 8;

    private final List<Semester> semesters = new ArrayList<Semester>(maxSemesters);

    public ModulePlanner() {}

    /**
     * Add one or more module(s) to list of modules taken for the specified semester.
     *
     * @param modules A list of valid modules to be added.
     * @param semester A valid semester.
     */
    public void addModulesToSemester(List<Module> modules, int semester) {

    }

    /**
     * Delete one or more module(s) from list of modules taken for the specified semester.
     *
     * @param modules A list of valid modules to be deleted.
     * @param semester A valid semester.
     */
    public void deleteModulesFromSemester(List<Module> modules, int semester) {

    }

    /**
     * Returns list of modules the user has taken or is planning to take in the specified semester.
     *
     * @param semester A valid semester.
     * @return A list of modules taken in the semester.
     */
    public List<Module> listModulesTakenForSemester(int semester) {
        return new ArrayList<Module>();
    }

    /**
     * Returns list of modules that the user is available to take for the specified semester.
     *
     * @param semester A valid semester.
     * @return A list of modules suggested for the semester.
     */
    public List<Module> listModulesSuggestedForSemester(int semester) {
        return new ArrayList<Module>();
    }
}
