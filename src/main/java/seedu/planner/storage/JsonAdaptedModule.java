package seedu.planner.storage;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

// @@author rongjicomputer

/**
 * JSON serializable {@code Module} class.
 */
public class JsonAdaptedModule {
    private String type;
    private String moduleCode;

    /**
     * Default constructor for JSON deserialization.
     */
    public JsonAdaptedModule() {}

    /**
     * Conversion
     * @param module {@code Module} to be converted.
     */
    public JsonAdaptedModule(Module module) {
        this.type = module.getType().toString();
        this.moduleCode = module.getCode();
    }

    public Module toModelType() throws IllegalValueException {
        return new Module(ModuleType.fromString(type), ModuleInfo.getFromModuleCode(moduleCode).get());
    }
}
