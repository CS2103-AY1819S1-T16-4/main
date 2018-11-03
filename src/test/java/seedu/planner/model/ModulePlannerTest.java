package seedu.planner.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2040;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.planner.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.planner.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.planner.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.getTypicalModulePlanner;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.testutil.ModulePlannerBuilder;

public class ModulePlannerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ModulePlanner modulePlanner = new ModulePlanner();

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modulePlanner.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyModulePlanner_replacesData() {
        ModulePlanner newData = getTypicalModulePlanner();
        modulePlanner.resetData(newData);
        assertEquals(newData, modulePlanner);
    }

    @Test
    public void addModules_invalidIndex_throwsIndexOutOfBoundsException() {
        thrown.expect(IndexOutOfBoundsException.class);
        modulePlanner.addModules(getTypicalModules(), 8);
    }

    @Test
    public void addModules_validIndex_success() {
        modulePlanner.addModules(getTypicalModules(), 0);
        assertEquals(new HashSet<>(modulePlanner.getTakenModules(0)), getTypicalModules());
    }

    @Test
    public void deleteModules_validModule_success() {
        ModulePlanner modulePlanner = new ModulePlannerBuilder()
                .withModuleAt(VALID_MODULE_CS1010, INDEX_FIRST)
                .withModuleAt(VALID_MODULE_CS2030, INDEX_SECOND)
                .withModuleAt(VALID_MODULE_CS2040, INDEX_SECOND)
                .withModuleAt(VALID_MODULE_CS2103T, INDEX_THIRD)
                .build();
        modulePlanner.deleteModules(Set.of(VALID_MODULE_CS1010));
        ModulePlanner expectedModulePlanner = new ModulePlannerBuilder()
                .build();
        assertEquals(expectedModulePlanner, modulePlanner);
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modulePlanner.hasModule(null);
    }

    @Test
    public void hasModule_moduleNotInModulePlanner_returnsFalse() {
        assertFalse(modulePlanner.hasModule(CS1010));
    }

    @Test
    public void hasModule_moduleInModulePlanner_returnsTrue() {
        modulePlanner.addModules(getTypicalModules(), INDEX_FIRST);
        assertTrue(modulePlanner.hasModule(CS1010));
    }

    @Test
    public void getModulesTaken_sameIndex_returnsSameList() {
        modulePlanner.addModules(getTypicalModules(), 0);
        assertEquals(modulePlanner.getTakenModules(0), modulePlanner.getTakenModules(0));
    }

    @Test
    public void getModulesTaken_differentIndex_returnsDifferentList() {
        modulePlanner.addModules(getTypicalModules(), 0);
        assertNotEquals(modulePlanner.getTakenModules(0), modulePlanner.getTakenModules(1));
    }

    @Test
    public void getModulesTaken_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modulePlanner.getTakenModules(0).remove(0);
    }

    @Test
    public void getModulesAvailable_sameModules_returnsSameList() {
        ModulePlanner differentModulePlanner = new ModulePlanner();
        assertEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void getModulesAvailable_differentModules_returnsDifferentList() {
        ModulePlanner differentModulePlanner = getTypicalModulePlanner();
        assertNotEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void equals() {
        modulePlanner.addModules(getTypicalModules(), 1);
        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), 1);

        // same modules in same semester -> returns true
        assertTrue(modulePlanner.equals(differentModulePlanner));

        // same object -> returns true
        assertTrue(modulePlanner.equals(modulePlanner));

        // null -> returns false
        assertFalse(modulePlanner.equals(null));

        // different types -> returns false
        assertFalse(modulePlanner.equals(5));

        // different modules in different semester -> returns false
        assertFalse(modulePlanner.equals(getTypicalModulePlanner()));

    }
}
