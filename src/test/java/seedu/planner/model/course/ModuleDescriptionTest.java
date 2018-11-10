package seedu.planner.model.course;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class ModuleDescriptionTest {
    @Test
    public void equals() {
        ModuleDescription cs1010 = new ModuleDescription("CS1010", ProgrammeRequirement.FOUNDATION);

        // same object -> returns true
        assertTrue(cs1010.equals(cs1010));

        // null -> return false
        assertFalse(cs1010.equals(null));

        // different type -> returns false
        assertFalse(cs1010.equals(5));

        // different module code -> returns false
        ModuleDescription cs1231 = new ModuleDescription("CS1231", ProgrammeRequirement.FOUNDATION);
        assertFalse(cs1010.equals(cs1231));

        // different programme requirement -> returns false
        ModuleDescription cs1010Fake = new ModuleDescription("CS1010", ProgrammeRequirement.IT_PROFESSIONALISM);
        assertFalse(cs1010.equals(cs1010Fake));
    }
}
