package seedu.planner.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.model.util.ModuleUtil.isModuleAvailableToTake;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.CS1231;
import static seedu.planner.testutil.TypicalModules.CS2040;
import static seedu.planner.testutil.TypicalModules.CS2103T;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.planner.model.course.ModuleDescription;
import seedu.planner.model.course.ProgrammeRequirement;
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
        List<Module> modules = new ArrayList<>(getTypicalModules());
        assertTrue(isModuleAvailableToTake(new ArrayList<>(), new ArrayList<>(), CS1010));
        assertTrue(isModuleAvailableToTake(modules, modules, CS2103T));
    }

    @Test
    public void isModuleAvailableToTake_moduleNotAvailable_returnsFalse() {
        List<Module> modules = new ArrayList<>(getTypicalModules());
        assertFalse(isModuleAvailableToTake(new ArrayList<>(), new ArrayList<>(), CS2040));
        assertFalse(isModuleAvailableToTake(modules, modules, CS1231));
        assertFalse(isModuleAvailableToTake(modules, new ArrayList<>(), CS2040));
    }

    // @@author rongjiecomputer
    @Test
    public void matchModuleCodePrefixes() {
        List<String> prefixes = List.of("CS", "ES", "CEG");
        // Module code in list
        assertTrue(ModuleUtil.matchModuleCodePrefixes("CS1010", prefixes));
        assertTrue(ModuleUtil.matchModuleCodePrefixes("ES1010", prefixes));

        // Module code not in list
        assertFalse(ModuleUtil.matchModuleCodePrefixes("AC1000", prefixes));

        // Only true if the whole alphabets part of module code is matched.
        assertTrue(ModuleUtil.matchModuleCodePrefixes("CEG1010", prefixes));
        assertFalse(ModuleUtil.matchModuleCodePrefixes("CE1010", prefixes));
    }

    @Test
    public void rankModuleCodePrefixes() {
        List<String> prefixes = List.of("CS", "CEG", "CE", "IS");

        // Module code in list
        assertEquals(ModuleUtil.rankModuleCodePrefixes("CS1010", prefixes), 0);

        // Prefix must match alphabets part entirely
        assertEquals(ModuleUtil.rankModuleCodePrefixes("CEG1010", prefixes), 1);
        assertEquals(ModuleUtil.rankModuleCodePrefixes("CE1010", prefixes), 2);

        // Module code not in list
        assertEquals(ModuleUtil.rankModuleCodePrefixes("AC1010", prefixes), 4);
    }

    @Test
    public void rankModuleCodeFromPriorityList() {
        List<ModuleDescription> priorityList = new ArrayList<>();
        priorityList.add(new ModuleDescription("CS1010", ProgrammeRequirement.FOUNDATION));
        priorityList.add(new ModuleDescription("CS1231", ProgrammeRequirement.FOUNDATION));
        priorityList.add(new ModuleDescription("CS2030", ProgrammeRequirement.FOUNDATION));

        // Module code in list
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("CS1010", priorityList), 0);
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("CS2030", priorityList), 2);

        // Module code not in list
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("CS2040", priorityList), 3);
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("GEQ1000", priorityList), 3);
    }
}
