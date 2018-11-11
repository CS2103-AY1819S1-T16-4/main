package seedu.planner.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;
import seedu.planner.model.semester.Semester;
import seedu.planner.model.user.UserProfile;

//@@author GabrielYik

/**
 * Represents an unmodifiable view of a {@code ModulePlanner}.
 */
public interface ReadOnlyModulePlanner {
    /**
     * Returns user profile object.
     *
     * Do not modify the content of the object.
     */
    UserProfile getUserProfile();

    /**
     * Returns an unmodifiable view of the {@code Semester}s.
     *
     * @return An {@code ObservableList} of the {@code Semester}s
     */
    List<Semester> getSemesters();

    /**
     * Returns all {@code Module}s taken in the {@code Semester} wrapped in an
     * {@code ObservableList}.
     *
     * @param index An integer between 0 to 7 inclusive, signifying a year and semester.
     * @return A list of modules taken in the semester.
     */
    ObservableList<Module> getTakenModulesForIndex(int index);

    /**
     * Retrieves all {@code Module}s available wrapped in an {@code ObservableList}.
     *
     * @return An {@code ObservableList} containing all the {@code Module}s available to be taken.
     */
    ObservableList<Module> getAvailableModules();

    /**
     * Retrieves {@code Module}s taken wrapped in an {@code ObservableList}.
     *
     * @return An {@code ObservableList} containing he {@code Module}s that user has taken.
     */
    ObservableList<Module> getTakenModules();
}
