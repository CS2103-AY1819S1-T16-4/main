package seedu.planner.storage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.model.module.ModuleInfo;

//@@author rongjiecomputer
public class JsonModuleInfoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModuleInfoStorageTest");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void readUserPrefs_nullFilePath_throwsNullPointerException() throws DataConversionException {
        thrown.expect(NullPointerException.class);
        readModuleInfo(null);
    }

    private Optional<ModuleInfo[]> readModuleInfo(String moduleInfoFileInTestDataFolder)
            throws DataConversionException {
        Path moduleInfoFilePath = addToTestDataPathIfNotNull(moduleInfoFileInTestDataFolder);
        return new JsonModuleInfoStorage(moduleInfoFilePath).readModuleInfo();
    }

    @Test
    public void readModuleInfo_missingFile_emptyResult() throws DataConversionException {
        assertFalse(readModuleInfo("NonExistentFile.json").isPresent());
    }

    @Test
    public void readModuleInfo_notJsonFormat_exceptionThrown() throws DataConversionException {
        thrown.expect(DataConversionException.class);
        readModuleInfo("NotJsonFormatModuleInfo.json");

        /* IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
         * That means you should not have more than one exception test in one method
         */
    }

    private Path addToTestDataPathIfNotNull(String moduleInfoFileInTestDataFolder) {
        return moduleInfoFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(moduleInfoFileInTestDataFolder)
                : null;
    }

    @Test
    public void sanityCheck() throws DataConversionException {
        JsonModuleInfoStorage storage = new JsonModuleInfoStorage(Paths.get("data", "moduleInfo.json"));
        Optional<ModuleInfo[]> optionalModuleInfo = storage.readModuleInfo();

        assertTrue(optionalModuleInfo.isPresent());
        ModuleInfo[] moduleInfo = optionalModuleInfo.get();
        System.out.println(moduleInfo.length);

        for (int i = 0; i < Math.min(10, moduleInfo.length); i++) {
            System.out.println(moduleInfo[i]);
        }
    }
}
