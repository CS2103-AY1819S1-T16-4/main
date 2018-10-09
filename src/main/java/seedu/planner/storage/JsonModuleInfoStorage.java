package seedu.planner.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.commons.util.JsonUtil;
import seedu.planner.model.module.ModuleInfo;

/**
 * A class to access ModuleInfo stored in the hard disk as a json file
 */
public class JsonModuleInfoStorage implements ModuleInfoStorage {
    public Path getModuleInfoFilePath() {
        return Paths.get("data", "moduleInfo.json");
    }

    @Override
    public Optional<ModuleInfo> readModuleInfo() {
        return Optional.empty();
    }
}
