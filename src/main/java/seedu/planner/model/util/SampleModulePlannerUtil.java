package seedu.planner.model.util;

import static seedu.planner.model.ModulePlanner.MAX_NUMBER_SEMESTERS;

import java.util.ArrayList;
import java.util.List;

import seedu.planner.model.ModulePlanner;
import seedu.planner.model.semester.Semester;

/**
 * Represents a temporary class to generate sample {@code ModulePlanner}s.
 */
public class SampleModulePlannerUtil {
    private static final List<Semester> semesters = new ArrayList<>(MAX_NUMBER_SEMESTERS);

    /**
     * Generates a sample {@code ModulePlanner}.
     *
     * @return A sample {@code ModulePlanner}
     */
    public static ModulePlanner genModulePlanner() {
        int year = 1;
        for (int i = 0; i < MAX_NUMBER_SEMESTERS; i++) {
            if (i != 0 && i % 2 == 0) {
                year++;
            }
            Semester semester = new Semester(i, year, true);
            semester.addModules(SampleModulesUtil.genModules(0, 4));
            semester.addAvailableModules(SampleModulesUtil.genModules(2, 6));
            semesters.add(i, semester);
        }
        ModulePlanner modulePlanner = new ModulePlanner();
        modulePlanner.setSemesters(semesters);
        return modulePlanner;
    }
}
