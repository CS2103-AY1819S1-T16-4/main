package seedu.planner.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.planner.commons.events.model.ModulePlannerChangedEvent;
import seedu.planner.commons.events.storage.DataSavingExceptionEvent;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.ReadOnlyModulePlanner;
import seedu.planner.model.UserPrefs;
import seedu.planner.ui.testutil.EventsCollectorRule;

public class StorageManagerTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private StorageManager storageManager;

    @Before
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonModulePlannerStorage modulePlannerStorage = new JsonModulePlannerStorage(getTempFilePath("mp"));
        storageManager = new StorageManager(modulePlannerStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.getRoot().toPath().resolve(fileName);
    }


    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(300, 600, 4, 6);
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void modulePlannerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonModulePlannerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonModulePlannerStorageTest} class.
         */
        ModulePlanner original = new ModulePlanner();
        storageManager.saveModulePlanner(original);
        ReadOnlyModulePlanner retrieved = storageManager.readModulePlanner().get();
        assertEquals(original, new ModulePlanner(retrieved));
    }

    @Test
    public void getModulePlannerFilePath() {
        assertNotNull(storageManager.getModulePlannerFilePath());
    }


    @Test
    public void handleModulePlannerChangedEvent_exceptionThrown_eventRaised() {
        // Create a StorageManager while injecting a stub that throws an exception when the save method is called
        Storage storage = new StorageManager(new JsonModulePlannerStorageExceptionThrowingStub(Paths.get("dummy")),
                new JsonUserPrefsStorage(Paths.get("dummy")));
        storage.handleModulePlannerChangedEvent(new ModulePlannerChangedEvent(new ModulePlanner()));
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof DataSavingExceptionEvent);
    }


    /**
     * A Stub class to throw an exception when the save method is called
     */
    class JsonModulePlannerStorageExceptionThrowingStub extends JsonModulePlannerStorage {

        public JsonModulePlannerStorageExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveModulePlanner(ReadOnlyModulePlanner modulePlanner, Path filePath) throws IOException {
            throw new IOException("dummy exception");
        }
    }

}
