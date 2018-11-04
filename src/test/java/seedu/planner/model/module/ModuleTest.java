package seedu.planner.model.module;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.CS2040;

import org.junit.Test;

public class ModuleTest {
    @Test
    public void attributes() {
        // Sanity check for various getters and toString methods.

        assertEquals(CS1010.getName(), "Programming Methodology");
        assertEquals(CS1010.getCode(), "CS1010");
        assertTrue(CS1010.getCreditCount() == 4.0);
        assertTrue(CS1010.getDescription().length() > 0);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(CS1010.equals(CS1010));

        // null -> return false
        assertFalse(CS1010.equals(null));

        // different type -> returns false
        assertFalse(CS1010.equals(5));

        // different module code -> returns false
        assertFalse(CS1010.equals(CS2040));
    }
}

