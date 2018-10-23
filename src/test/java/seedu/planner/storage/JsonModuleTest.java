package seedu.planner.storage;

import java.io.IOException;

import org.junit.Test;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.commons.exceptions.IllegalValueException;
import seedu.planner.commons.util.JsonUtil;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.semester.Semester;

public class JsonModuleTest {
    @Test
    public void test() throws DataConversionException, IOException, IllegalValueException {
        ModuleInfo.ModuleInfoRetriever retriever = ModuleInfo.ModuleInfoRetriever.getInstance();

        {
            String str = "{\"type\": \"University Level Requirements\", \"moduleCode\": \"CS1231\"}";
            JsonAdaptedModule jm = JsonUtil.fromJsonString(str, JsonAdaptedModule.class);
            Module m = jm.toModelType();
            System.out.println(m);
        }

        {
            String str = "{\"index\": 1, \"year\": 1, \"hasBeenTaken\": true," +
                    "\"modulesTaken\": [" +
                    "  {\"type\": \"University Level Requirements\", \"moduleCode\": \"CS2040\"}" +
                    "]," +
                    "\"modulesAvailable\": []" +
                    "}";
            JsonAdaptedSemester jsem = JsonUtil.fromJsonString(str, JsonAdaptedSemester.class);
            Semester sem = jsem.toModelType();
            System.out.println(sem.getModulesTaken().get(0).getPrerequisites());
        }
    }
}
