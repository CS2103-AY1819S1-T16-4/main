package seedu.planner.model;

import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;
import seedu.planner.model.semester.Semester;

//@@author GabrielYik

/**
 * Represents an unmodifiable view of a {@code ModulePlanner}.
 */
public interface ReadOnlyModulePlanner {

    /**
     * Returns an unmodifiable view of the {@code Semester}s.
     *
     * @return An {@code ObservableList} of the {@code Semester}s
     */
    ObservableList<Semester> getSemesters();

    /**
     * Returns an unmodifiable view of the {@code Module}s taken.
     *
     * @param semesterIndex The nominal semester index the {@code Module}s
     *                      are stored at
     * @return An {@code ObservableList} of the {@code Module}s
     */
    ObservableList<Module> getModulesTakenFromSemester(int semesterIndex);

    /**
     * Returns an unmodifiable view of the {@code Module}s available.
     *
     * @param semesterIndex The nominal semester index the {@code Module}s
     *                      are stored at
     * @return An {@code ObservableList} of the {@code Module}s
     */
    ObservableList<Module> getModulesAvailableFromSemester(int semesterIndex);
}