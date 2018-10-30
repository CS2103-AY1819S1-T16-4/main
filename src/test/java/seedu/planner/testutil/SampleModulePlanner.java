package seedu.planner.testutil;

import static seedu.planner.model.util.IndexUtil.convertYearAndSemesterToIndex;

import seedu.planner.model.ModulePlanner;
import seedu.planner.model.semester.Semester;

/**
 * A class that generates a sample module planner for testing.
 */
public class SampleModulePlanner {
    /**
     * Generates a sample {@code ModulePlanner}.
     *
     * @return A sample {@code ModulePlanner}
     */
    public static ModulePlanner getModulePlanner() {
        ModulePlanner modulePlanner = new ModulePlanner();
        for (int year = 1; year <= 4; year++) {
            for (int sem = 1; sem <= 2; sem++) {
                Semester semester = new Semester(year, sem);
                semester.addModules(SampleModules.getModules(0, 4));
                modulePlanner.addModules(semester.getModules(),
                        convertYearAndSemesterToIndex(year, sem));
            }
        }

        return modulePlanner;
    }
}
