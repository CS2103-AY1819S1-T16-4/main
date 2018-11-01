package seedu.planner.model.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.model.util.ModuleUtil.isModuleAvailableToTake;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.CS1020;
import static seedu.planner.testutil.TypicalModules.CS2040;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.planner.model.module.Module;

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

    //@@author Hilda-Ang

    @Test
    public void isModuleAvailableToTake_moduleAvailable_returnsTrue() {
        List<Module> modules = new ArrayList<>();
        modules.addAll(getTypicalModules());
        assertTrue(isModuleAvailableToTake(new ArrayList<>(), new ArrayList<>(), CS1010));
        assertTrue(isModuleAvailableToTake(modules, modules, CS2040));
    }

    @Test
    public void isModuleAvailableToTake_moduleNotAvailable_returnsFalse() {
        List<Module> modules = new ArrayList<>();
        modules.addAll(getTypicalModules());
        assertFalse(isModuleAvailableToTake(new ArrayList<>(), new ArrayList<>(), CS2040));
        assertFalse(isModuleAvailableToTake(modules, modules, CS1020));
        assertFalse(isModuleAvailableToTake(modules, new ArrayList<>(), CS2040));
    }
}
