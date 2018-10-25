package seedu.planner.testutil;

import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

/**
 * A utility class to help with building {@code Module} objects.
 */
public class ModuleBuilder {

    public static final ModuleType DEFAULT_TYPE = ModuleType.PROGRAMME_REQUIREMENTS;
    public static final ModuleInfo DEFAULT_INFORMATION = new ModuleInfo();

    private ModuleType type;
    private ModuleInfo information;

    public ModuleBuilder() {
        type = DEFAULT_TYPE;
        information = DEFAULT_INFORMATION;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        type = moduleToCopy.getType();
        information = new ModuleInfoBuilder()
            .withCode(moduleToCopy.getCode())
            .withName(moduleToCopy.getName())
            .withPossibleTypes(moduleToCopy.getPossibleTypes())
            .withCreditCount(moduleToCopy.getCreditCount())
            .withPreclusions(moduleToCopy.getPreclusions().stream().map(m -> m.getCode()).toArray(String[]::new))
            .withPrerequisites(moduleToCopy.getPrerequisites().stream().map(m -> m.getCode()).toArray(String[]::new))
            .build();
    }

    /**
     * Sets the type of the {@code Module} that we are building.
     */
    public ModuleBuilder withType(ModuleType type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the information of the {@code Module} that we are building.
     */
    public ModuleBuilder withInformation(ModuleInfo information) {
        this.information = information;
        return this;
    }

    public Module build() {
        return new Module(type, information);
    }
}
