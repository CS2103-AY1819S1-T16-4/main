package seedu.planner.testutil;

import static seedu.planner.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.planner.model.ModulePlanner;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

/**
 * A utility class containing {@code Modules} to be used.
 */
public class TypicalModules {

    private static final ModuleInfo CS1010_INFORMATION = new ModuleInfoBuilder()
        .withCode("CS1010")
        .withName("Programming Methodology")
        .withPossibleTypes(new ModuleType[] { ModuleType.PROGRAMME_REQUIREMENTS, ModuleType.UNRESTRICTED_ELECTIVES })
        .withCreditCount(4)
        .withPreclusions(new String[] {"CS1101S"})
        .withPrerequisites(new String[] {})
        .build();

    private static final ModuleInfo CS2030_INFORMATION = new ModuleInfoBuilder()
        .withCode("CS2030")
        .withName("Programming Methodology II")
        .withPossibleTypes(new ModuleType[] { ModuleType.PROGRAMME_REQUIREMENTS, ModuleType.UNRESTRICTED_ELECTIVES })
        .withCreditCount(4)
        .withPreclusions(new String[] {"CS1020"})
        .withPrerequisites(new String[] {"CS1010"})
        .build();

    public static final Module CS1010 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(CS1010_INFORMATION).build();

    public static final Module CS2030 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(CS2030_INFORMATION).build();

    private TypicalModules() {}

    /**
     * Returns an {@code ModulePlanner} with all the typical modules.
     */
    public static ModulePlanner getTypicalModulePlanner() {
        ModulePlanner mp = new ModulePlanner();
        for (Module module : getTypicalModules()) {
            List<Module> moduleList = new ArrayList<>();
            moduleList.add(module);
            mp.addModules(moduleList, INDEX_FIRST);
        }
        return mp;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS1010, CS2030));
    }
}
