package seedu.planner.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.model.util.ModuleUtil.isModuleAvailable;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.CS1231;
import static seedu.planner.testutil.TypicalModules.CS2040;
import static seedu.planner.testutil.TypicalModules.CS2103T;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import seedu.planner.model.course.DegreeRequirement;
import seedu.planner.model.course.ModuleDescription;
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
    public void isModuleAvailable_moduleAvailable_returnsTrue() {
        List<Module> modules = new ArrayList<>(getTypicalModules());

        // module has no prerequisite
        assertTrue(isModuleAvailable(new ArrayList<>(), new ArrayList<>(), CS1010));

        // module's prerequisites has been fulfilled
        assertTrue(isModuleAvailable(modules, modules, CS2103T));
    }

    @Test
    public void isModuleAvailable_moduleNotAvailable_returnsFalse() {
        List<Module> modules = new ArrayList<>(getTypicalModules());

        // module's prerequisites have not been fulfilled
        assertFalse(isModuleAvailable(new ArrayList<>(), new ArrayList<>(), CS2040));

        // module has been taken
        assertFalse(isModuleAvailable(modules, modules, CS1231));
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
        priorityList.add(new ModuleDescription("CS1010", DegreeRequirement.FOUNDATION));
        priorityList.add(new ModuleDescription("CS1231", DegreeRequirement.FOUNDATION));
        priorityList.add(new ModuleDescription("CS2030", DegreeRequirement.FOUNDATION));

        // Module code in list
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("CS1010", priorityList), 0);
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("CS2030", priorityList), 2);

        // Module code not in list
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("CS2040", priorityList), 3);
        assertEquals(ModuleUtil.rankModuleCodeFromPriorityList("GEQ1000", priorityList), 3);
    }

    @Test
    public void findModuleEquivalence() {
        List<Module> moduleList = List.of(new Module("CS1010"), new Module("CS1101S"));

        //Empty List
        assertEquals(ModuleUtil.findModuleEquivalences(new ArrayList<>()), new ArrayList<>());

        //Non-Empty List
        assertEquals(ModuleUtil.findModuleEquivalences(moduleList),
                List.of(List.of(new Module("CS1010"), new Module("CS1101S"))));
    }
}
