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

    public static final Module CS1010 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS1010").get()).build();

    public static final Module CS2030 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS2030").get()).build();

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
