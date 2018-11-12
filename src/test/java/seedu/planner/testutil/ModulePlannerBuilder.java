package seedu.planner.testutil;

import static seedu.planner.testutil.TypicalIndexes.INDEX_ZERO;

import java.util.HashSet;
import java.util.Set;

import seedu.planner.model.ModulePlanner;
import seedu.planner.model.module.Module;

/**
 * A utility class to help with building ModulePlanner objects.
 * Example usage: <br>
 *     {@code ModulePlanner mp = new ModulePlannerBuilder().withModule(CS1010, CS2030).build();}
 */
public class ModulePlannerBuilder {

    private ModulePlanner modulePlanner;

    public ModulePlannerBuilder() {
        modulePlanner = new ModulePlanner();
    }

    public ModulePlannerBuilder(ModulePlanner modulePlanner) {
        this.modulePlanner = modulePlanner;
    }

    /**
     * Adds a {@code Module} to the {@code ModulePlanner} that we are building.
     */
    public ModulePlannerBuilder withModule(Module module) {
        Set<Module> modules = new HashSet<>();
        modules.add(module);
        modulePlanner.addModules(modules, INDEX_ZERO);
        return this;
    }

    /**
     * Adds a {@code Module} to the specified semester {@code index}
     * in the {@code ModulePlanner} that we are building.
     *
     * @param module The module to be added
     * @param index The semester index
     * @return {@code this}
     */
    public ModulePlannerBuilder withModuleAt(Module module, int index) {
        Set<Module> modules = new HashSet<>();
        modules.add(module);
        modulePlanner.addModules(modules, index);
        return this;
    }

    public ModulePlanner build() {
        return modulePlanner;
    }
}
