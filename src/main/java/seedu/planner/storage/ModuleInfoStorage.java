package seedu.planner.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

//@@author rongjiecomputer
/**
 * Represents a storage for {@link seedu.planner.model.module.ModuleInfo}.
 */
public interface ModuleInfoStorage {

    /**
     * Returns the file path of the ModuleInfo data file.
     */
    Path getModuleInfoFilePath();

    /**
     * Returns UserPrefs data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException If the data in storage is not in the expected format
     * @throws IOException If there was any problem when reading from the storage
     */
    Optional<ModuleInfo[]> readModuleInfo() throws DataConversionException, IOException;

    /**
     * Returns {@code ModuleInfo} of a certain {@code ModuleType} that {@code ModuleInfoStorage} stores.
     *
     * @param moduleType Type of module
     * @return A list of {@code ModuleInfo} of {@code moduleType}
     */
    List<ModuleInfo> getModuleInfo(ModuleType moduleType);
}
