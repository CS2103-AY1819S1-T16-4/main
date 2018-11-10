package seedu.planner.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static seedu.planner.testutil.TypicalModules.CS2040;
import static seedu.planner.testutil.TypicalModules.CS2103T;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.ReadOnlyModulePlanner;
import seedu.planner.model.course.Major;
import seedu.planner.model.user.UserProfile;

public class JsonModulePlannerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModulePlannerStorageTest");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void readModulePlanner_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readModulePlanner(null);
    }

    private java.util.Optional<ReadOnlyModulePlanner> readModulePlanner(String filePath) throws Exception {
        return new JsonModulePlannerStorage(Paths.get(filePath))
                .readModulePlanner(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModulePlanner("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {

        thrown.expect(DataConversionException.class);
        readModulePlanner("NotJsonFormatModulePlanner.json");

        /* IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
         * That means you should not have more than one exception test in one method
         */
    }

    @Test
    public void readModulePlanner_invalidModuleModulePlanner_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readModulePlanner("invalidModuleModulePlanner.json");
    }

    @Test
    public void readModulePlanner_invalidAndValidModuleModulePlanner_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readModulePlanner("invalidAndValidModuleModulePlanner.json");
    }

    @Test
    public void readAndSaveModulePlanner_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempModulePlanner.xml");
        ModulePlanner original = getTypicalModulePlanner();
        JsonModulePlannerStorage jsonModulePlannerStorage = new JsonModulePlannerStorage(filePath);

        //Save in new file and read back
        jsonModulePlannerStorage.saveModulePlanner(original, filePath);
        ReadOnlyModulePlanner readBack = jsonModulePlannerStorage.readModulePlanner(filePath).get();
        assertEquals(original, new ModulePlanner(readBack));

        //Modify data, overwrite exiting file, and read back
        original.deleteModules(Set.of(CS2040));
        original.addModules(Set.of(CS2103T), 2);
        jsonModulePlannerStorage.saveModulePlanner(original, filePath);
        readBack = jsonModulePlannerStorage.readModulePlanner(filePath).get();
        assertEquals(original, new ModulePlanner(readBack));

        //Save and read without specifying file path
        original.setUserProfile(new UserProfile(Major.COMPUTER_ENGINEERING, new ArrayList<>()));
        jsonModulePlannerStorage.saveModulePlanner(original); //file path not specified
        readBack = jsonModulePlannerStorage.readModulePlanner().get(); //file path not specified
        assertEquals(original, new ModulePlanner(readBack));

    }

    @Test
    public void saveModulePlanner_nullModulePlanner_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveModulePlanner(null, "SomeFile.xml");
    }

    /**
     * Saves {@code ModulePlanner} at the specified {@code filePath}.
     */
    private void saveModulePlanner(ReadOnlyModulePlanner modulePlanner, String filePath) {
        try {
            new JsonModulePlannerStorage(Paths.get(filePath))
                    .saveModulePlanner(modulePlanner, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveModulePlanner_nullFilePath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveModulePlanner(new ModulePlanner(), null);
    }

}
