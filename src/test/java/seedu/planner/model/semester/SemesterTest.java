package seedu.planner.model.semester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.testutil.TypicalModules.CS1010;
import static seedu.planner.testutil.TypicalModules.CS2030;
import static seedu.planner.testutil.TypicalModules.getTypicalModules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import seedu.planner.model.module.Module;

public class SemesterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final Semester semester = new Semester(1, 0);

    @Test
    public void addModulesTaken_success() {
        semester.addModules(getTypicalModules());
        assertEquals(semester.getModulesTaken(), getTypicalModules());
    }

    @Test
    public void deleteModules_modulesInSemester_success() {
        semester.addModules(getTypicalModules());
        List<Module> modules = new ArrayList<>();
        modules.add(CS1010);
        semester.deleteModules(modules);

        assertFalse(semester.containsModule(CS1010));
        assertTrue(semester.containsModule(CS2030));
    }

    @Test
    public void containsModule_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        semester.containsModule(null);
    }

    @Test
    public void containsModule_moduleInSemester_returnsTrue() {
        semester.addModules(getTypicalModules());

        assertTrue(semester.containsModule(CS1010));
    }

    @Test
    public void containsModule_moduleNotInSemester_returnsFalse() {
        assertFalse(semester.containsModule(CS1010));
    }

    @Test
    public void equals() {
        // same index, year, and modules -> returns true
        assertTrue(semester.equals(new Semester(1, 0)));

        // same object -> returns true
        assertTrue(semester.equals(semester));

        // null -> returns false
        assertFalse(semester.equals(null));

        // different type -> returns false
        assertFalse(semester.equals(5));

        // different year -> returns false
        assertFalse(semester.equals(new Semester(2, 0)));

        // different index -> returns false
        assertFalse(semester.equals(new Semester(1, 1)));

        // different modules -> returns false
        Semester differentSemester = new Semester(1, 0);
        differentSemester.addModules(getTypicalModules());
        assertFalse(semester.equals(differentSemester));
    }
}
