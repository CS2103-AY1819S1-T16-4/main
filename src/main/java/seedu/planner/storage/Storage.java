package seedu.planner.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.planner.commons.events.model.ModulePlannerChangedEvent;
import seedu.planner.commons.events.storage.DataSavingExceptionEvent;
import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.model.ReadOnlyModulePlanner;
import seedu.planner.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ModulePlannerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    Path getModulePlannerFilePath();

    @Override
    Optional<ReadOnlyModulePlanner> readModulePlanner() throws DataConversionException;

    @Override
    Optional<ReadOnlyModulePlanner> readModulePlanner(Path filePath) throws DataConversionException;

    @Override
    void saveModulePlanner(ReadOnlyModulePlanner modulePlanner) throws IOException;

    @Override
    void saveModulePlanner(ReadOnlyModulePlanner modulePlanner, Path filePath) throws IOException;

    /**
     * Saves the current version of the Module Planner to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleModulePlannerChangedEvent(ModulePlannerChangedEvent mpce);
}
