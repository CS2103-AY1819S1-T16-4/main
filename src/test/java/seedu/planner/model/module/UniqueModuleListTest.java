package seedu.planner.model.module;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.CS2030;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.planner.model.module.exceptions.DuplicateModuleException;
import seedu.planner.model.module.exceptions.ModuleNotFoundException;

public class UniqueModuleListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleList.contains(null);
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(CS1010));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.add(CS1010);
        assertTrue(uniqueModuleList.contains(CS1010));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleList.add(null);
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        uniqueModuleList.add(CS1010);
        thrown.expect(DuplicateModuleException.class);
        uniqueModuleList.add(CS1010);
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleList.setModule(null, CS1010);
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleList.setModule(CS1010, null);
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
        thrown.expect(ModuleNotFoundException.class);
        uniqueModuleList.setModule(CS1010, CS1010);
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        uniqueModuleList.add(CS1010);
        uniqueModuleList.setModule(CS1010, CS1010);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS1010);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        uniqueModuleList.add(CS1010);
        uniqueModuleList.setModule(CS1010, CS2030);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2030);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
        uniqueModuleList.add(CS1010);
        uniqueModuleList.add(CS2030);
        thrown.expect(DuplicateModuleException.class);
        uniqueModuleList.setModule(CS1010, CS2030);
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleList.remove(null);
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        thrown.expect(ModuleNotFoundException.class);
        uniqueModuleList.remove(CS1010);
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.add(CS1010);
        uniqueModuleList.remove(CS1010);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleList.setModules((UniqueModuleList) null);
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.add(CS1010);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2030);
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueModuleList.setModules((List<Module>) null);
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.add(CS1010);
        List<Module> moduleList = Collections.singletonList(CS2030);
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(CS2030);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(CS1010, CS1010);
        thrown.expect(DuplicateModuleException.class);
        uniqueModuleList.setModules(listWithDuplicateModules);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueModuleList.asUnmodifiableObservableList().remove(0);
    }
}
