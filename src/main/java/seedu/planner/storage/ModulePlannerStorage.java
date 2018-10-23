package seedu.planner.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.model.ReadOnlyModulePlanner;

// @@author rongjiecomputer
/**
 * Represents a storage for {@link seedu.planner.model.ModulePlanner}.
 */
public interface ModulePlannerStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getModulePlannerFilePath();

    /**
     * Returns ModulePlanner data as a {@link ReadOnlyModulePlanner}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     */
    Optional<ReadOnlyModulePlanner> readModulePlanner() throws DataConversionException;

    /**
     * @see #readModulePlanner()
     */
    Optional<ReadOnlyModulePlanner> readModulePlanner(Path filePath) throws DataConversionException;

    /**
     * Saves the given {@link ReadOnlyModulePlanner} to the storage.
     * @param modulePlanner cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveModulePlanner(ReadOnlyModulePlanner modulePlanner) throws IOException;

    /**
     * @see #saveModulePlanner(ReadOnlyModulePlanner)
     */
    void saveModulePlanner(ReadOnlyModulePlanner modulePlanner, Path filePath) throws IOException;
}
