package seedu.planner.testutil;

import static seedu.planner.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.planner.model.ModulePlanner;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

/**
 * A utility class containing {@code Modules} to be used.
 */
public class TypicalModules {

    public static final Module CS1010 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS1010").get()).build();

    public static final Module CS1020 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS1020").get()).build();

    public static final Module CS2030 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS2030").get()).build();

    public static final Module CS2040 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS2040").get()).build();

    private TypicalModules() {}

    /**
     * Returns an {@code ModulePlanner} with all the typical modules.
     */
    public static ModulePlanner getTypicalModulePlanner() {
        ModulePlanner mp = new ModulePlanner();
        for (Module module : getTypicalModules()) {
            Set<Module> modules = new HashSet<>();
            modules.add(module);
            mp.addModules(modules, INDEX_FIRST);
        }
        return mp;
    }

    public static Set<Module> getTypicalModules() {
        return new HashSet<>(Arrays.asList(CS1010, CS2030));
    }
}
