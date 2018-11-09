package seedu.planner.storage;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.commons.util.JsonUtil;
import seedu.planner.model.ModulePlanner;
import seedu.planner.testutil.TypicalModules;

public class JsonSerializableModulePlannerTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableModulePlannerTest");
    private static final Path TYPICAL_MODULE_PLANNER_FILE = TEST_DATA_FOLDER.resolve("typicalModulePlanner.json");
    private static final Path INVALID_MODULE_FILE = TEST_DATA_FOLDER.resolve("invalidModuleModulePlanner.json");
    private static final Path DUPLICATE_MODULE_SINGLE_SEMESTER_FILE =
            TEST_DATA_FOLDER.resolve("duplicateModuleSingleSemester.json");
    private static final Path DUPLICATE_MODULE_ACROSS_SEMESTERS_FILE =
            TEST_DATA_FOLDER.resolve("duplicateModuleAcrossSemesters.json");
    private static final Path INVALID_USER_PROFILE_FILE = TEST_DATA_FOLDER.resolve("invalidMajor.json");
    private static final Path INVALID_FOCUS_AREA_FILE = TEST_DATA_FOLDER.resolve("invalidFocusArea.json");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalModulePlannerFile_success() throws Exception {
        JsonSerializableModulePlanner dataFromFile = JsonUtil.readJsonFile(TYPICAL_MODULE_PLANNER_FILE,
                JsonSerializableModulePlanner.class).get();
        ModulePlanner modulePlannerFromFile = dataFromFile.toModelType();
        ModulePlanner typicalModulePlanner = TypicalModules.getTypicalModulePlanner();
        assertEquals(modulePlannerFromFile, typicalModulePlanner);
    }

    @Test
    public void toModelType_invalidModuleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModulePlanner dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_FILE,
                JsonSerializableModulePlanner.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_duplicateModuleSingleSemesterFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModulePlanner dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_SINGLE_SEMESTER_FILE,
                JsonSerializableModulePlanner.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_duplicateModuleAcrossSemestersFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModulePlanner dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_ACROSS_SEMESTERS_FILE,
                JsonSerializableModulePlanner.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_invalidUserProfile() throws Exception {
        JsonSerializableModulePlanner dataFromFile = JsonUtil.readJsonFile(INVALID_USER_PROFILE_FILE,
                JsonSerializableModulePlanner.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_invalidFocusArea() throws Exception {
        JsonSerializableModulePlanner dataFromFile = JsonUtil.readJsonFile(INVALID_FOCUS_AREA_FILE,
                JsonSerializableModulePlanner.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }
}
