package seedu.planner.testutil;

import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

/**
 * A utility class to help with building {@code ModuleInfo} objects.
 */
public class ModuleInfoBuilder {

    public static final String DEFAULT_CODE = "CS2030";
    public static final String DEFAULT_NAME = "Programming Methodology II";
    public static final ModuleType[] DEFAULT_POSSIBLE_TYPES = {ModuleType.PROGRAMME_REQUIREMENTS,
        ModuleType.UNRESTRICTED_ELECTIVES};
    public static final float DEFAULT_CREDIT_COUNT = 4;
    public static final String[] DEFAULT_PRECLUSIONS = new String[] {"CS1020"};
    public static final String[] DEFAULT_PREREQUISITES = new String[] {"CS1010"};

    private String code;
    private String name;
    private ModuleType[] possibleTypes;
    private float creditCount;
    private String[] preclusions;
    private String[] prerequisites;

    public ModuleInfoBuilder() {
        code = DEFAULT_CODE;
        name = DEFAULT_NAME;
        possibleTypes = DEFAULT_POSSIBLE_TYPES;
        creditCount = DEFAULT_CREDIT_COUNT;
        preclusions = DEFAULT_PRECLUSIONS;
        prerequisites = DEFAULT_PREREQUISITES;
    }

    /**
     * Initializes the ModuleInfoBuilder with the data of {@code moduleInfoToCopy}.
     */
    public ModuleInfoBuilder(ModuleInfo moduleInfoToCopy) {
        code = moduleInfoToCopy.getCode();
        name = moduleInfoToCopy.getName();
        possibleTypes = moduleInfoToCopy.getPossibleTypes().clone();
        creditCount = moduleInfoToCopy.getCreditCount();
        preclusions = moduleInfoToCopy.getPreclusions().stream().map(m -> m.getCode()).toArray(String[]::new);
        prerequisites = moduleInfoToCopy.getPrerequisites().stream().map(m -> m.getCode()).toArray(String[]::new);
    }

    /**
     * Sets the code of the {@code ModuleInfo} that we are building.
     */
    public ModuleInfoBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Sets the name of the {@code ModuleInfo} that we are building.
     */
    public ModuleInfoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the possible types of the {@code ModuleInfo} that we are building.
     */
    public ModuleInfoBuilder withPossibleTypes(ModuleType[] possibleTypes) {
        this.possibleTypes = possibleTypes.clone();
        return this;
    }

    /**
     * Sets the credit count of the {@code ModuleInfo} that we are building.
     */
    public ModuleInfoBuilder withCreditCount(float creditCount) {
        this.creditCount = creditCount;
        return this;
    }

    /**
     * Sets the preclusions of the {@code ModuleInfo} that we are building.
     */
    public ModuleInfoBuilder withPreclusions(String[] preclusions) {
        this.preclusions = preclusions.clone();
        return this;
    }

    /**
     * Sets the prerequisites of the {@code ModuleInfo} that we are building.
     */
    public ModuleInfoBuilder withPrerequisites(String[] prerequisites) {
        this.prerequisites = prerequisites.clone();
        return this;
    }

    public ModuleInfo build() {
        return new ModuleInfo(code, name, possibleTypes, creditCount, preclusions, prerequisites);
    }

}
