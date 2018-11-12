package seedu.planner.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2040;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ONE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_TWO;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ZERO;
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
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        assertEquals(new HashSet<>(modulePlanner.getTakenModulesForIndex(INDEX_ZERO)), getTypicalModules());
    }

    @Test
    public void deleteModules_validModule_success() {
        ModulePlanner modulePlanner = new ModulePlannerBuilder()
                .withModuleAt(VALID_MODULE_CS1010, INDEX_ZERO)
                .withModuleAt(VALID_MODULE_CS2030, INDEX_ONE)
                .withModuleAt(VALID_MODULE_CS2040, INDEX_ONE)
                .withModuleAt(VALID_MODULE_CS2103T, INDEX_TWO)
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
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        assertTrue(modulePlanner.hasModule(CS1010));
    }

    @Test
    public void getTakenModulesForIndex_sameIndex_returnsSameList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        assertEquals(modulePlanner.getTakenModulesForIndex(INDEX_ZERO),
                     modulePlanner.getTakenModulesForIndex(INDEX_ZERO));
    }

    @Test
    public void getTakenModulesForIndex_differentIndex_returnsDifferentList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        assertNotEquals(modulePlanner.getTakenModulesForIndex(INDEX_ZERO),
                        modulePlanner.getTakenModulesForIndex(INDEX_ONE));
    }

    @Test
    public void listTakenModulesAll_noModulesTaken_sameTakenModulesList() {
        modulePlanner.listTakenModulesAll();

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.listTakenModulesAll();

        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
    }

    @Test
    public void listTakenModulesAll_sameModulesTakenInSameYear_sameTakenModulesList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        modulePlanner.listTakenModulesAll();

        // Add modules to same year same semester
        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        differentModulePlanner.listTakenModulesAll();

        // Add modules to same year different semesters
        ModulePlanner differentModulePlanner1 = new ModulePlanner();
        differentModulePlanner1.addModules(getTypicalModules(), INDEX_ONE);
        differentModulePlanner1.listTakenModulesAll();

        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner1.getTakenModules());
    }

    @Test
    public void listTakenModulesAll_sameModulesTakenInDifferentYears_sameTakenModulesList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        modulePlanner.listTakenModulesAll();

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_TWO);
        differentModulePlanner.listTakenModulesAll();

        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
    }

    @Test
    public void listTakenModulesAll_differentModulesTaken_differentTakenModulesList() {
        modulePlanner.listTakenModulesAll();

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_TWO);
        differentModulePlanner.listTakenModulesAll();

        assertNotEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
    }

    @Test
    public void listTakenModulesForYear_noModulesTaken_sameTakenModulesList() {
        modulePlanner.listTakenModulesForYear(INDEX_ONE);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.listTakenModulesForYear(INDEX_ONE);

        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
    }

    @Test
    public void listTakenModulesForYear_sameModulesTakenInSameYearListSameYear_sameTakenModulesList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        modulePlanner.listTakenModulesForYear(INDEX_ONE);

        // Add modules to same year same semester
        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        differentModulePlanner.listTakenModulesForYear(INDEX_ONE);

        // Add modules to same year different semester
        ModulePlanner differentModulePlanner1 = new ModulePlanner();
        differentModulePlanner1.addModules(getTypicalModules(), INDEX_ONE);
        differentModulePlanner1.listTakenModulesForYear(INDEX_ONE);

        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner1.getTakenModules());
    }

    @Test
    public void listTakenModulesForYear_differentModulesTakenInSameYearListSameYear_differentTakenModulesList() {
        modulePlanner.listTakenModulesForYear(INDEX_ONE);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        differentModulePlanner.listTakenModulesForYear(INDEX_ONE);

        assertNotEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
    }

    @Test
    public void listTakenModulesForYear_sameModulesTakenInSameYearListDifferentYears_differentTakenModulesList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        modulePlanner.listTakenModulesForYear(INDEX_ONE);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        differentModulePlanner.listTakenModulesForYear(INDEX_TWO);

        assertNotEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
    }

    @Test
    public void getTakenModulesForIndex_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modulePlanner.getTakenModulesForIndex(INDEX_ZERO).remove(INDEX_ZERO);
    }

    @Test
    public void getTakenModules_noModulesTaken_returnsSameList() {
        ModulePlanner differentModulePlanner = new ModulePlanner();
        assertEquals(modulePlanner.getTakenModules(), differentModulePlanner.getTakenModules());
    }

    @Test
    public void getTakenModules_sameModulesTaken_returnsSameList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);

        assertEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void getTakenModules_differentModulesTaken_returnsDifferentList() {
        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);

        assertNotEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void getAvailableModules_noModulesTaken_returnsSameList() {
        ModulePlanner differentModulePlanner = new ModulePlanner();
        assertEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void suggestModules_noModulesTaken_sameAvailableModulesList() {
        modulePlanner.suggestModules(INDEX_ZERO);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.suggestModules(INDEX_ONE);

        assertEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void suggestModules_sameModulesTakenSuggestSameIndex_sameAvailableModulesList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        modulePlanner.suggestModules(INDEX_ZERO);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        differentModulePlanner.suggestModules(INDEX_ZERO);

        assertEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void suggestModules_sameModulesTakenSuggestDifferentIndex_differentAvailableModulesList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        modulePlanner.suggestModules(INDEX_ZERO);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        differentModulePlanner.suggestModules(INDEX_ONE);

        assertNotEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void suggestModules_differentModulesTakenSuggestSameIndex_differentAvailableModulesList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);
        modulePlanner.suggestModules(INDEX_ZERO);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.suggestModules(INDEX_ZERO);

        assertNotEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void getAvailableModules_sameModulesTaken_returnsSameList() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ZERO);

        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ZERO);

        assertEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void getAvailableModules_differentModulesTaken_returnsDifferentList() {
        ModulePlanner differentModulePlanner = getTypicalModulePlanner();
        assertNotEquals(modulePlanner.getAvailableModules(), differentModulePlanner.getAvailableModules());
    }

    @Test
    public void equals() {
        modulePlanner.addModules(getTypicalModules(), INDEX_ONE);
        ModulePlanner differentModulePlanner = new ModulePlanner();
        differentModulePlanner.addModules(getTypicalModules(), INDEX_ONE);

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
