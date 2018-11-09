package seedu.planner.storage;

import java.util.Optional;

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
    public JsonAdaptedModule() {
    }

    /**
     * Conversion
     *
     * @param module {@code Module} to be converted.
     */
    public JsonAdaptedModule(Module module) {
        this.type = module.getType().toString();
        this.moduleCode = module.getCode();
    }

    /**
     * Convert to {@code Module} object.
     *
     * @throws IllegalValueException
     */
    public Module toModelType() throws IllegalValueException {
        Optional<ModuleInfo> optionalModuleInfo = ModuleInfo.getFromModuleCode(moduleCode);
        if (!optionalModuleInfo.isPresent()) {
            throw new IllegalValueException(ModuleInfo.MESSAGE_MODULE_CODE_NOT_FOUND);
        }
        return new Module(ModuleType.fromString(type), optionalModuleInfo.get());
    }
}
