package seedu.planner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import seedu.planner.commons.core.Config;
import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.core.Version;
import seedu.planner.commons.events.ui.ExitAppRequestEvent;
import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.commons.util.ConfigUtil;
import seedu.planner.commons.util.StringUtil;
import seedu.planner.logic.Logic;
import seedu.planner.logic.LogicManager;
import seedu.planner.model.AddressBook;
import seedu.planner.model.Model;
import seedu.planner.model.ModelManager;
import seedu.planner.model.ReadOnlyAddressBook;
import seedu.planner.model.UserPrefs;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.util.SampleDataUtil;
import seedu.planner.storage.AddressBookStorage;
import seedu.planner.storage.JsonModuleInfoStorage;
import seedu.planner.storage.JsonUserPrefsStorage;
import seedu.planner.storage.ModuleInfoStorage;
import seedu.planner.storage.Storage;
import seedu.planner.storage.StorageManager;
import seedu.planner.storage.UserPrefsStorage;
import seedu.planner.storage.XmlAddressBookStorage;
import seedu.planner.ui.Ui;
import seedu.planner.ui.UiManager;

/**
 * The main entry point to the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;
    protected UserPrefs userPrefs;


    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        userPrefs = initPrefs(userPrefsStorage);

        AddressBookStorage addressBookStorage = new XmlAddressBookStorage(userPrefs.getAddressBookFilePath());
        ModuleInfoStorage moduleInfoStorage = new JsonModuleInfoStorage(userPrefs.getModuleInfoFilePath());
        storage = new StorageManager(addressBookStorage, moduleInfoStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model);

        ui = new UiManager(logic, config, userPrefs);

        initEventsCenter();
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s planner book and {@code userPrefs}. <br>
     * The data from the sample planner book will be used instead if {@code storage}'s planner book is not found,
     * or an empty planner book will be used instead if errors occur when reading {@code storage}'s planner book.
     */
    private Model initModelManager(Storage storage, UserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        Optional<ModuleInfo[]> moduleInfoOptional;
        ModuleInfo[] initialModuleInfo;

        try {
            moduleInfoOptional = storage.readModuleInfo();
            if (!moduleInfoOptional.isPresent()) {
                logger.info("Module info file not found. Will be starting with a sample module database");
            }
            // TODO(rongjiecomputer) Actually use sample data.
            initialModuleInfo = new ModuleInfo[] {};
        } catch (DataConversionException e) {
            logger.warning("Module info file not in the correct format."
                    + " Will be starting with an empty module database");
            initialModuleInfo = new ModuleInfo[] {};
        } catch (IOException e) {
            logger.warning("Porblem while reading from the file. Will be starting with an empty module database");
            initialModuleInfo = new ModuleInfo[] {};
        }

        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        }

        return new ModelManager(initialData, initialModuleInfo, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    private void initEventsCenter() {
        EventsCenter.getInstance().registerHandler(this);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Module Planner " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        ui.stop();
        try {
            storage.saveUserPrefs(userPrefs);
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
        Platform.exit();
        System.exit(0);
    }

    @Subscribe
    public void handleExitAppRequestEvent(ExitAppRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
