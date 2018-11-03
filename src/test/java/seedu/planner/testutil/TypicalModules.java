package seedu.planner.testutil;

import static seedu.planner.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.planner.testutil.TypicalIndexes.INDEX_SECOND;

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

    public static final Module CS1231 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
            .withInformation(ModuleInfo.getFromModuleCode("CS1231").get()).build();

    public static final Module CS1020 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS1020").get()).build();

    public static final Module CS2030 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS2030").get()).build();

    public static final Module CS2040 = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
        .withInformation(ModuleInfo.getFromModuleCode("CS2040").get()).build();

    public static final Module CS2103T = new ModuleBuilder().withType(ModuleType.PROGRAMME_REQUIREMENTS)
            .withInformation(ModuleInfo.getFromModuleCode("CS2103T").get()).build();



    private TypicalModules() {}

    /**
     * Returns an {@code ModulePlanner} with all the typical modules.
     */
    public static ModulePlanner getTypicalModulePlanner() {
        return new ModulePlannerBuilder()
                .withModuleAt(CS1010, INDEX_FIRST)
                .withModuleAt(CS1231, INDEX_FIRST)
                .withModuleAt(CS2030, INDEX_SECOND)
                .withModuleAt(CS2040, INDEX_SECOND)
                .build();
    }

    public static Set<Module> getTypicalModules() {
        return Set.of(CS1010, CS1231, CS2030, CS2040);
    }
}
