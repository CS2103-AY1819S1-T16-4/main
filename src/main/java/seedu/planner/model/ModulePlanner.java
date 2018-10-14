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
                semesters.add(new Semester(j, i, false));
            }
        }
    }

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
    public List<Module> listModulesTakenForSemester(int year, int semester) {
        int index = year * 2 - (2 - semester);
        return new ArrayList<Module>(semesters.get(index).listModulesTaken());
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
