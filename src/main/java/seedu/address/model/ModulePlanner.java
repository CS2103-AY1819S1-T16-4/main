package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.semester.Semester;

/**
 * Wraps all data at the module planner level, holds 8 semesters.
 */
public class ModulePlanner {
    private final List<Semester> semesters = new ArrayList<Semester>(8);

    public ModulePlanner() {}

    /**
     * Add modules to specified semester.
     *
     * @param modules A list of valid modules to be added.
     * @param semester A valid semester.
     */
    public void addModulesToSemester(List<Module> modules, int semester) {

    }

    /**
     * Delete modules from specified semester.
     *
     * @param modules A list of valid modules to be deleted.
     * @param semester A valid semester.
     */
    public void deleteModulesFromSemester(List<Module> modules, int semester) {

    }

    /**
     * Returns list of modules taken in specified semester.
     *
     * @param semester A valid semester.
     * @return A list of modules taken in the semester.
     */
    public List<Module> listModulesTakenForSemester(int semester) {
        return new ArrayList<Module>();
    }

    /**
     * Returns list of modules suggested for specified semester.
     *
     * @param semester A valid semester.
     * @return A list of modules suggested for the semester.
     */
    public List<Module> listModulesSuggestedForSemester(int semester) {
        return new ArrayList<Module>();
    }
}
