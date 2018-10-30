package seedu.planner.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.ReadOnlyModulePlanner;
import seedu.planner.model.semester.Semester;
import seedu.planner.model.user.UserProfile;

// @@author rongjiecomputer

/**
 * An Immutable {@link ModulePlanner} that is serializable to JSON format.
 */
public class JsonSerializableModulePlanner {
    private List<JsonAdaptedSemester> semesters;
    private UserProfile userProfile;

    /**
     * Default constructor for JSON serialization.
     */
    public JsonSerializableModulePlanner() {
        semesters = new ArrayList<>(ModulePlanner.MAX_NUMBER_SEMESTERS);
        userProfile = new UserProfile();
    }

    /**
     * Conversion.
     */
    public JsonSerializableModulePlanner(ReadOnlyModulePlanner src) {
        this();
        semesters.addAll(src.getSemesters().stream().map(JsonAdaptedSemester::new).collect(Collectors.toList()));
        userProfile = src.getUserProfile();
    }

    /**
     * Converts this moduleplanner into the model's [@code ModulePlanner] object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModulePlanner toModelType() throws IllegalValueException {
        ModulePlanner planner = new ModulePlanner();
        List<Semester> semesters = new ArrayList<>(ModulePlanner.MAX_NUMBER_SEMESTERS);
        for (JsonAdaptedSemester sem : this.semesters) {
            semesters.add(sem.toModelType());
        }

        planner.setModulesInSemesters(semesters);
        planner.setUserProfile(userProfile);

        return planner;
    }
}
