package seedu.planner.model.util;

import java.util.List;

import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

/**
 * Represents a temporary class to generate sample {@code Module}s.
 */
public class SampleModulesUtil {

    // @@author rongjiecomputer

    /**
     * Returns an {@code ObservableList<Module>} with modules from {@code start} to {@code end}.
     * The possible modules are stored in a {@code List} internally.
     *
     * @param start The start index
     * @param end   The end index
     * @return An {@code ObservableList} with modules from {@code start} to {@code end}
     */
    public static List<Module> genModules(int start, int end) {
        ModuleType[] pt1 = new ModuleType[]{ModuleType.PR_FOUNDATION, ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt2 = new ModuleType[]{ModuleType.PR_BREADTH_AND_DEPTH, ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt3 = new ModuleType[]{ModuleType.PR_IT_PROFESSIONALISM, ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt4 = new ModuleType[]{ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt5 = new ModuleType[]{ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt6 = new ModuleType[]{ModuleType.PR_INDUSTRIAL_EXPERIENCE};

        ModuleInfo cs1010 = new ModuleInfo("CS1010", "Programming Methodology I", pt1,
                4, new String[]{}, new String[]{});

        ModuleInfo cs1231 = new ModuleInfo("CS1231", "Discrete structure", pt2,
                4, new String[]{}, new String[]{});

        ModuleInfo cs2030 = new ModuleInfo("CS2030", "Programming Methodology II", pt3,
                4, new String[]{}, new String[]{"CS1010"});

        ModuleInfo cs2040 = new ModuleInfo("CS2040", "Data Structure", pt1,
                4, new String[]{"CS2040C"}, new String[]{"CS1010", "CS1231"});

        ModuleInfo cs2040c = new ModuleInfo("CS2040C", "Data Structure", pt1,
                4, new String[]{"CS2040"}, new String[]{"CS1010", "CS1231"});

        ModuleInfo cs1234 = new ModuleInfo("CS1234", "Sleep Science", pt4,
                100, new String[]{"CS2030"}, new String[]{"CS2040"});

        ModuleInfo cs5678 = new ModuleInfo("CS5678", "Eat more gum", pt5,
                6, new String[]{}, new String[]{"CS1010"});

        ModuleInfo cs9101 = new ModuleInfo("CS9101", "Beef Heef", pt6,
                5, new String[]{}, new String[]{});

        ModuleInfo.finalizeModuleInfo(
                new ModuleInfo[]{cs1010, cs1231, cs2030, cs2040, cs2040c, cs1234, cs5678, cs9101});

        Module m1 = new Module(ModuleType.PR_FOUNDATION, cs1010);
        Module m2 = new Module(ModuleType.PR_BREADTH_AND_DEPTH, cs1231);
        Module m3 = new Module(ModuleType.PR_IT_PROFESSIONALISM, cs2030);
        Module m4 = new Module(ModuleType.PR_FOUNDATION, cs2040);
        Module m5 = new Module(ModuleType.PR_FOUNDATION, cs2040c);
        Module m6 = new Module(ModuleType.UNRESTRICTED_ELECTIVES, cs5678);
        Module m7 = new Module(ModuleType.PR_INDUSTRIAL_EXPERIENCE, cs9101);

        List<Module> modules = List.of(m1, m2, m3, m4, m5, m6, m7);

        return modules.subList(start, end);
    }
}
