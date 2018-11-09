package seedu.planner.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.model.module.Module;
import seedu.planner.model.semester.Semester;

// @@author rongjiecomputer

/**
 * JSON serializable {@code Semester} class.
 */
public class JsonAdaptedSemester {
    public static final String MESSAGE_DUPLCIATE_MODULE = "Duplicate module(s) in a semester.";

    private int index;
    private int year;

    // Indicator of whether user has passed the index
    private boolean hasBeenTaken;

    // Modules lists
    private List<JsonAdaptedModule> modulesTaken = new ArrayList<>();

    /**
     * Default constructor. For JSON Deserialization.
     */
    JsonAdaptedSemester() {}

    /**
     * Conversion
     * @param sem {@code Semester} to be converted.
     */
    JsonAdaptedSemester(Semester sem) {
        index = sem.getIndex();
        year = sem.getYear();
        modulesTaken = sem.getModules().stream().map(
            m -> new JsonAdaptedModule(m)).collect(Collectors.toList());
    }

    /**
     * Convert to {@code Semester} object.
     *
     * @throws IllegalValueException
     */
    public Semester toModelType() throws IllegalValueException {
        Set<Module> modulesTaken = new HashSet<>();
        for (JsonAdaptedModule jsonAdaptedModule : this.modulesTaken) {
            Module toModelType = jsonAdaptedModule.toModelType();
            if (modulesTaken.contains(toModelType)) {
                throw new IllegalValueException(MESSAGE_DUPLCIATE_MODULE);
            }
            modulesTaken.add(toModelType);
        }
        Semester sem = new Semester(year, index);
        sem.addModules(modulesTaken);
        return sem;
    }
}
