package seedu.planner.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.planner.testutil.TypicalModules.CS1010;

import java.io.IOException;

import org.junit.Test;

import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.commons.util.JsonUtil;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;
import seedu.planner.testutil.Assert;

public class JsonAdaptedModuleTest {
    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule cs1010 = new JsonAdaptedModule(CS1010);
        assertEquals(CS1010, cs1010.toModelType());
    }

    @Test
    public void toModelType_invalidModuleType_throwsIllegalValueException() throws IOException {
        String s = "{\"type\": \"invalid\", \"moduleCode\": \"CS1010\"}";
        JsonAdaptedModule module = JsonUtil.fromJsonString(s, JsonAdaptedModule.class);
        String expectedMessage = ModuleType.MESSAGE_UNKNOWN_MODULE_TYPE;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() throws IOException {
        String s = "{\"type\": \"Programme Requirements\", \"moduleCode\": \"invalid\"}";
        JsonAdaptedModule module = JsonUtil.fromJsonString(s, JsonAdaptedModule.class);
        String expectedMessage = ModuleInfo.MESSAGE_MODULE_CODE_NOT_FOUND;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}
