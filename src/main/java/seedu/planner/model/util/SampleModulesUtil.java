package seedu.planner.model.util;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

/**
 * Represents a temporary class to generate sample {@code Module}s.
 */
public class SampleModulesUtil {

    /**
     * Returns an {@code ObservableList<Module>} with modules from {@code start} to {@code end}.
     * The possible modules are stored in a {@code List} internally.
     *
     * @param start The start index
     * @param end The end index
     * @return An {@code ObservableList} with modules from {@code start} to {@code end}
     */
    public static ObservableList<Module> genModules(int start, int end) {
        ModuleType[] pt1 = new ModuleType[] { ModuleType.PR_FOUNDATION, ModuleType.UNRESTRICTED_ELECTIVES };
        ModuleType[] pt2 = new ModuleType[] { ModuleType.PR_BREADTH_AND_DEPTH, ModuleType.UNRESTRICTED_ELECTIVES };
        ModuleType[] pt3 = new ModuleType[] { ModuleType.PR_IT_PROFESSIONALISM, ModuleType.UNRESTRICTED_ELECTIVES };
        ModuleType[] pt4 = new ModuleType[] { ModuleType.UNRESTRICTED_ELECTIVES };
        ModuleType[] pt5 = new ModuleType[] { ModuleType.UNRESTRICTED_ELECTIVES };
        ModuleType[] pt6 = new ModuleType[] { ModuleType.PR_INDUSTRIAL_EXPERIENCE };

        ModuleInfo cs1010 = new ModuleInfo("CS1010", "Programming Methodology", pt1,
                4, new ModuleInfo[] {}, new ModuleInfo[] {});

        ModuleInfo cs1231 = new ModuleInfo("CS1231", "Discrete structure", pt2,
                4, new ModuleInfo[] {}, new ModuleInfo[] {});

        ModuleInfo cs2030 = new ModuleInfo("CS2030", "Programming Methodology II", pt3,
                4, new ModuleInfo[] {}, new ModuleInfo[] {cs1010});

        ModuleInfo cs2040 = new ModuleInfo("CS2040", "Data Structure", pt1,
                4, new ModuleInfo[] {}, new ModuleInfo[] { cs1010, cs1231 });

        ModuleInfo cs1234 = new ModuleInfo("CS1234", "Sleep Science", pt4,
                100, new ModuleInfo[] { cs2030 }, new ModuleInfo[] { cs2040 });

        ModuleInfo cs5678 = new ModuleInfo("CS5678", "Eat more gum", pt5,
                6, new ModuleInfo[] {}, new ModuleInfo[] { cs1010 });

        ModuleInfo cs9101 = new ModuleInfo("CS9101", "Beef Heef", pt6,
                5, new ModuleInfo[] {}, new ModuleInfo[] {});

        Module m1 = new Module(ModuleType.PR_FOUNDATION, cs1010);
        Module m2 = new Module(ModuleType.PR_BREADTH_AND_DEPTH, cs1231);
        Module m3 = new Module(ModuleType.PR_IT_PROFESSIONALISM, cs2030);
        Module m4 = new Module(ModuleType.PR_FOUNDATION, cs2040);
        Module m5 = new Module(ModuleType.UNRESTRICTED_ELECTIVES, cs1234);
        Module m6 = new Module(ModuleType.UNRESTRICTED_ELECTIVES, cs5678);
        Module m7 = new Module(ModuleType.PR_INDUSTRIAL_EXPERIENCE, cs9101);

        List<Module> modules = List.of(m1, m2, m3, m4, m5, m6, m7);

        return FXCollections.observableList(modules.subList(start, end));
    }
}
