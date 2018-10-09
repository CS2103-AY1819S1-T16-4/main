package seedu.planner.model.module;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a temporary class to generator sample {@code Module}s.
 */
public class ModuleGenerator {

    /**
     * Returns a specified number of {@code ObservableList}s.
     *
     * @param count Number of modules to be returned in the {@code ObservableList}
     * @return An {@code ObservableList} with the specified number of modules in {@code count}
     */
    public static ObservableList<Module> genModules(int count) {
        ModuleType[] pt1 = new ModuleType[] { ModuleType.PR_FOUNDATION, ModuleType.UNRESTRICTED_ELECTIVES };
        ModuleType[] pt2 = new ModuleType[] { ModuleType.PR_BREADTH_AND_DEPTH, ModuleType.UNRESTRICTED_ELECTIVES };
        ModuleType[] pt3 = new ModuleType[] { ModuleType.PR_IT_PROFESSIONALISM, ModuleType.UNRESTRICTED_ELECTIVES };

        ModuleInformation mi1 = new ModuleInformation("CS1234", "m1", pt1,
                4, new Module[] { new Module("CS1111") },
                new Module[] { new Module("MA2222"), new Module("BC1342")});
        ModuleInformation mi2 = new ModuleInformation("CS5678", "m2", pt2,
                4, new Module[] { new Module("PC5555") },
                new Module[] { new Module("IS0909")});
        ModuleInformation mi3 = new ModuleInformation("CS9101", "m3", pt3,
                4, new Module[] { new Module("ES2356")},
                new Module[] { new Module("CE1649")});

        Module m1 = new Module(ModuleType.PR_FOUNDATION, mi1);
        Module m2 = new Module(ModuleType.PR_BREADTH_AND_DEPTH, mi2);
        Module m3 = new Module(ModuleType.PR_IT_PROFESSIONALISM, mi3);

        List<Module> modules = List.of(m1, m2, m3);

        return FXCollections.observableList(modules.subList(0, count));
    }
}
