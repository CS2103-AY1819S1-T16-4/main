package seedu.planner.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.ReadOnlyModulePlanner;
import seedu.planner.model.course.FocusArea;
import seedu.planner.model.course.Major;
import seedu.planner.model.module.Module;
import seedu.planner.model.semester.Semester;
import seedu.planner.model.user.UserProfile;

// @@author rongjiecomputer

/**
 * An Immutable {@link ModulePlanner} that is serializable to JSON format.
 */
public class JsonSerializableModulePlanner {
    public static final String MESSAGE_DUPLICATE_MODULE = "Duplicate module(s) across semesters.";
    public static final String MESSAGE_INVALID_MAJOR = "Invalid major in user profile.";
    public static final String MESSAGE_INVALID_FOCUS_AREA = "Invalid focus area(s) in user profile.";

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

        // To check duplicate modules across semesters
        HashSet<Module> modules = new HashSet<>();

        for (JsonAdaptedSemester jsonSem : this.semesters) {
            Semester sem = jsonSem.toModelType();
            if (sem.getModules().stream().anyMatch(m -> modules.contains(m))) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            sem.getModules().stream().forEach(m -> modules.add(m));
            semesters.add(sem);
        }

        if (userProfile.getMajor() == Major.UNKNOWN) {
            throw new IllegalValueException(MESSAGE_INVALID_MAJOR);
        }

        if (userProfile.getFocusAreas().contains(FocusArea.UNKNOWN)) {
            throw new IllegalValueException(MESSAGE_INVALID_FOCUS_AREA);
        }

        planner.setModulesInSemesters(semesters);
        planner.setUserProfile(userProfile);

        return planner;
    }
}
