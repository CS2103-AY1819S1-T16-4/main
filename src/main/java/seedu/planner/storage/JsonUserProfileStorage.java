package seedu.planner.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.commons.util.FileUtil;
import seedu.planner.commons.util.JsonUtil;
import seedu.planner.model.user.UserProfile;

// @@author rongjiecomputer

/**
 * Represents a storage for {@link seedu.planner.model.user.UserProfile}.
 */
public class JsonUserProfileStorage implements UserProfileStorage {
    private static Logger logger = LogsCenter.getLogger(JsonUserProfileStorage.class);
    private Path filePath;

    public JsonUserProfileStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserProfileFilePath() {
        return filePath;
    }

    @Override
    public Optional<UserProfile> readUserProfile() throws DataConversionException {
        return readUserProfile(filePath);
    }

    /**
     * Similar to {@link #readUserProfile()}
     *
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<UserProfile> readUserProfile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("UserProfile file " + filePath + " not found");
            return Optional.empty();
        }

        Optional<UserProfile> userProfile = JsonUtil.readJsonFile(filePath, UserProfile.class);
        if (userProfile.isPresent()) {
            return Optional.of(userProfile.get());
        }
        return Optional.empty();
    }

    @Override
    public void saveUserProfile(UserProfile userProfile) throws IOException {
        saveUserProfile(userProfile, filePath);
    }

    /**
     * Similar to {@link #saveUserProfile(UserProfile)}
     *
     * @param filePath location of the data. Cannot be null
     */
    public void saveUserProfile(UserProfile userProfile, Path filePath) throws IOException {
        requireNonNull(userProfile);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(userProfile, filePath);
    }

}
