package seedu.planner.testutil;

import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2040;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ONE;
import static seedu.planner.testutil.TypicalIndexes.INDEX_ZERO;

import java.util.Set;

import seedu.planner.model.ModulePlanner;
import seedu.planner.model.module.Module;

/**
 * A utility class containing {@code Modules} to be used.
 */
public class TypicalModules {

    public static final Module CS1010 = VALID_MODULE_CS1010;

    public static final Module CS1231 = VALID_MODULE_CS1231;

    public static final Module CS2030 = VALID_MODULE_CS2030;

    public static final Module CS2040 = VALID_MODULE_CS2040;

    public static final Module CS2103T = VALID_MODULE_CS2103T;


    private TypicalModules() {}

    /**
     * Returns an {@code ModulePlanner} with all the typical modules.
     */
    public static ModulePlanner getTypicalModulePlanner() {
        return new ModulePlannerBuilder()
                .withModuleAt(CS1010, INDEX_ZERO)
                .withModuleAt(CS1231, INDEX_ZERO)
                .withModuleAt(CS2030, INDEX_ONE)
                .withModuleAt(CS2040, INDEX_ONE)
                .build();
    }

    public static Set<Module> getTypicalModules() {
        return Set.of(CS1010, CS1231, CS2030, CS2040);
    }
}
