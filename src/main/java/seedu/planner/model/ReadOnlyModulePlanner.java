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
     * Returns an unmodifiable view of the {@code Module}s taken.
     *
     * @param index The semester index the {@code Module}s are stored at
     * @return An {@code ObservableList} of the {@code Module}s
     */
    ObservableList<Module> getTakenModules(int index);

    /**
     * Returns an unmodifiable view of the {@code Module}s available.
     *
     * @return An {@code ObservableList} of the {@code Module}s
     */
    ObservableList<Module> getAvailableModules();

    ObservableList<Module> listTakenModules();
}
