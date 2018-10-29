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
        // @@author rongjiecomputer
        ModuleInfo cs1010 = ModuleInfo.getFromModuleCode("CS1010").get();
        ModuleInfo cs1231 = ModuleInfo.getFromModuleCode("CS1231").get();
        ModuleInfo cs2030 = ModuleInfo.getFromModuleCode("CS2030").get();
        ModuleInfo cs2040 = ModuleInfo.getFromModuleCode("CS2040").get();
        ModuleInfo cs2040c = ModuleInfo.getFromModuleCode("CS2040C").get();
        ModuleInfo cs2101 = ModuleInfo.getFromModuleCode("CS2101").get();
        ModuleInfo cs2103t = ModuleInfo.getFromModuleCode("CS2103T").get();
        ModuleInfo cs2105 = ModuleInfo.getFromModuleCode("CS2105").get();
        ModuleInfo ma1521 = ModuleInfo.getFromModuleCode("MA1521").get();
        ModuleInfo cp2106 = ModuleInfo.getFromModuleCode("CP2106").get();
        // @@author

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
