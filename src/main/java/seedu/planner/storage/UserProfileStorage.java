package seedu.planner.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.model.user.UserProfile;

// @@author rongjiecomputer

public interface UserProfileStorage {
    /**
     * Returns the file path of the UserProfile data file.
     */
    Path getUserProfileFilePath();

    /**
     * Returns UserProfile data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UserProfile> readUserProfile() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.planner.model.user.UserProfile} to the storage.
     * @param userProfile cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserProfile(UserProfile userProfile) throws IOException;
}
