package seedu.planner.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.planner.commons.core.ComponentManager;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.model.AddressBookChangedEvent;
import seedu.planner.commons.events.model.ModulePlannerChangedEvent;
import seedu.planner.commons.events.storage.DataSavingExceptionEvent;
import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.model.ReadOnlyAddressBook;
import seedu.planner.model.ReadOnlyModulePlanner;
import seedu.planner.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private ModulePlannerStorage modulePlannerStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(AddressBookStorage addressBookStorage, ModulePlannerStorage modulePlannerStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.modulePlannerStorage = modulePlannerStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ ModulePlanner methods ============================

    @Override
    public Path getModulePlannerFilePath() {
        return modulePlannerStorage.getModulePlannerFilePath();
    }

    @Override
    public Optional<ReadOnlyModulePlanner> readModulePlanner() throws DataConversionException {
        return readModulePlanner(modulePlannerStorage.getModulePlannerFilePath());
    }

    @Override
    public Optional<ReadOnlyModulePlanner> readModulePlanner(Path filePath) throws DataConversionException {
        logger.fine("Attempting to read data from file: " + filePath);
        return modulePlannerStorage.readModulePlanner(filePath);
    }

    @Override
    public void saveModulePlanner(ReadOnlyModulePlanner modulePlanner) throws IOException {
        saveModulePlanner(modulePlanner, modulePlannerStorage.getModulePlannerFilePath());
    }

    @Override
    public void saveModulePlanner(ReadOnlyModulePlanner modulePlanner, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        modulePlannerStorage.saveModulePlanner(modulePlanner, filePath);
    }

    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    @Subscribe
    public void handleAddressBookChangedEvent(AddressBookChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveAddressBook(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    @Override
    @Subscribe
    public void handleModulePlannerChangedEvent(ModulePlannerChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveModulePlanner(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }
}
