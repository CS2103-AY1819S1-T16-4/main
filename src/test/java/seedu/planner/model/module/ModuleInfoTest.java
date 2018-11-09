package seedu.planner.model.module;

import org.junit.Test;

public class ModuleInfoTest {
    @Test
    public void printToString() {
        // Sanity check to make sure toString does not crash.
        System.out.println(ModuleInfo.getFromModuleCode("CFG1010").get());

        // Test module with preclusions
        System.out.println(ModuleInfo.getFromModuleCode("GER1000").get());

        // Test module with prerequisites
        System.out.println(ModuleInfo.getFromModuleCode("CS2040").get());
    }
}
