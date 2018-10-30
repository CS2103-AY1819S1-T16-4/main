package seedu.planner.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.model.module.Module;
import seedu.planner.model.semester.Semester;

// @@author rongjiecomputer

/**
 * JSON serializable {@code Semester} class.
 */
public class JsonAdaptedSemester {
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
        modulesTaken = sem.getModulesTaken().stream().map(
            m -> new JsonAdaptedModule(m)).collect(Collectors.toList());
    }

    /**
     * Convert to {@code Semester} object.
     *
     * @throws IllegalValueException
     */
    public Semester toModelType() throws IllegalValueException {
        List<Module> modulesTaken = new ArrayList<>();
        for (JsonAdaptedModule jsonAdaptedModule : this.modulesTaken) {
            Module toModelType = jsonAdaptedModule.toModelType();
            modulesTaken.add(toModelType);
        }
        Semester sem = new Semester(year, index);
        sem.addModules(modulesTaken);
        return sem;
    }
}
