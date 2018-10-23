package seedu.planner.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.Optional;

import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.commons.util.FileUtil;
import seedu.planner.commons.util.JsonUtil;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.ReadOnlyModulePlanner;

// @@author rongjiecomputer

/**
 * An Immutable ModulePlanner that is serializable to JSON format
 */
public class JsonModulePlannerStorage implements ModulePlannerStorage {
    private static Logger logger = LogsCenter.getLogger(JsonModulePlannerStorage.class);
    private Path filePath;

    public JsonModulePlannerStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getModulePlannerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ModulePlanner> readModulePlanner() throws DataConversionException {
        return readModulePlanner(filePath);
    }

    /**
     * Similar to {@link #readModulePlanner}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ModulePlanner> readModulePlanner(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("ModulePlanner file " + filePath + " not found");
            return Optional.empty();
        }

        Optional<JsonSerializableModulePlanner> jsonModulePlanner = JsonUtil.readJsonFile(
            filePath, JsonSerializableModulePlanner.class);
        try {
            if (jsonModulePlanner.isPresent()) {
                return Optional.of(jsonModulePlanner.get().toModelType());
            }
            return Optional.empty();
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveModulePlanner(ReadOnlyModulePlanner addressBook) throws IOException {
        saveModulePlanner(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveModulePlanner(ReadOnlyModulePlanner)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveModulePlanner(ReadOnlyModulePlanner modulePlanner, Path filePath) throws IOException {
        requireNonNull(modulePlanner);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModulePlanner(modulePlanner), filePath);
    }

}
