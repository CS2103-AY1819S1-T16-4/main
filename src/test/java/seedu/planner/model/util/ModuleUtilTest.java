package seedu.planner.model.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ModuleUtilTest {

    //@@author GabrielYik

    @Test
    public void hasValidCodeFormat_validFormat_returnsTrue() {
        assertTrue(ModuleUtil.hasValidCodeFormat("CS1010"));
        assertTrue(ModuleUtil.hasValidCodeFormat("CS1010J"));
        assertTrue(ModuleUtil.hasValidCodeFormat("CS1010CS"));
        assertTrue(ModuleUtil.hasValidCodeFormat("CEG1010"));
        assertTrue(ModuleUtil.hasValidCodeFormat("CEG1010J"));
    }

    @Test
    public void hasValidCodeFormat_invalidFormat_returnsFalse() {
        assertFalse(ModuleUtil.hasValidCodeFormat("CS10101"));
        assertFalse(ModuleUtil.hasValidCodeFormat("CSCS1010"));
        assertFalse(ModuleUtil.hasValidCodeFormat("CSCS10101"));
        assertFalse(ModuleUtil.hasValidCodeFormat("CSCS10101JJJ"));
    }

    //@@author
}
