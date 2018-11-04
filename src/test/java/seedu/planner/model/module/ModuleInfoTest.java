package seedu.planner.model.module;

import org.junit.Test;

public class ModuleInfoTest {
    @Test
    public void printToString() {
        // Sanity check to make sure toString does not crash.
        System.out.println(ModuleInfo.getFromModuleCode("CS1010").get());
    }
}
