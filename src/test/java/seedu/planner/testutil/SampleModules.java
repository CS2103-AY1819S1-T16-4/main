package seedu.planner.testutil;

import java.util.List;

import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

/**
 * A utility class that generates sample modules for testing.
 */
public class SampleModules {

    /**
     * Returns a list of  modules from {@code start} to {@code end}.
     *
     * @param start The start index
     * @param end   The end index
     * @return A list with modules from {@code start} to {@code end}
     */
    public static List<Module> getModules(int start, int end) {
        ModuleType[] pt1 = new ModuleType[]{ModuleType.PR_FOUNDATION, ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt2 = new ModuleType[]{ModuleType.PR_BREADTH_AND_DEPTH, ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt3 = new ModuleType[]{ModuleType.PR_IT_PROFESSIONALISM, ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt4 = new ModuleType[]{ModuleType.UNRESTRICTED_ELECTIVES};
        ModuleType[] pt5 = new ModuleType[]{ModuleType.PR_INDUSTRIAL_EXPERIENCE};

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

        ModuleInfo cs2101 = new ModuleInfo("CS2101",
                "Effective Communication for Computing Professionals", pt1,
                4, new String[]{"CS2103"}, new String[]{"ES1000"});

        ModuleInfo cs2103t = new ModuleInfo("CS2103T", "Software Engineering", pt1,
                4, new String[]{"CS2103"}, new String[]{"CS2030"});

        ModuleInfo cs2105 = new ModuleInfo("CS2105", "Introduction to Computer Networks", pt1,
                4, new String[] {"IT2001"}, new String[]{"CS2030"});

        ModuleInfo ma1521 = new ModuleInfo("MA1521", "Calculus for Computing", pt2,
                4, new String[]{"MA1102R"}, new String[] {"MA1301"});

        ModuleInfo cp2106 = new ModuleInfo("CS2106", "Independent Software Development Project (Orbital)",
                pt4, 4, new String[]{}, new String[]{"CS1010"});

        ModuleInfo.finalizeModuleInfo(
                new ModuleInfo[]{cs1010, cs1231, cs2030, cs2040, cs2040c, cs2101, cs2103t, cs2105});

        Module m1 = new Module(ModuleType.PR_FOUNDATION, cs1010);
        Module m2 = new Module(ModuleType.PR_BREADTH_AND_DEPTH, cs1231);
        Module m3 = new Module(ModuleType.PR_IT_PROFESSIONALISM, cs2030);
        Module m4 = new Module(ModuleType.PR_FOUNDATION, cs2040);
        Module m5 = new Module(ModuleType.PR_FOUNDATION, cs2040c);
        Module m6 = new Module(ModuleType.UNRESTRICTED_ELECTIVES, cs2103t);
        Module m7 = new Module(ModuleType.PR_INDUSTRIAL_EXPERIENCE, cs2105);
        Module m8 = new Module(ModuleType.UNRESTRICTED_ELECTIVES, ma1521);
        Module m9 = new Module(ModuleType.PR_BREADTH_AND_DEPTH, cp2106);

        List<Module> modules = List.of(m1, m2, m3, m4, m5, m6, m7, m8, m9);

        return modules.subList(start, end);
    }
}
