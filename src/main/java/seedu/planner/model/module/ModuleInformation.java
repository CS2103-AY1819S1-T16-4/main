package seedu.planner.model.module;

//TODO

/**
 * Represents a temporary {@code ModuleInformation} class.
 */
public class ModuleInformation {
    public static final String MESSAGE_MODULE_CODE_CONSTRAINTS = "Module codes should be of the format WXY1234(Z), "
            + "where WXY refers to a prefix like CS or IS that describes the type of the module, "
            + "1234 refers to a sequence of positive numbers, "
            + "and (Z) refers to an optional postfix.";

    private String code;

    private String name;

    private ModuleType[] possibleTypes;

    private int creditCount;

    private Module[] preclusions;

    private Module[] prerequisites;

    private ModuleInformation(String code, String name, ModuleType[] possibleTypes,
                              int creditCount, Module[] preclusions, Module[] prerequisites) {
        this.code = code;
        this.name = name;
        this.possibleTypes = possibleTypes;
        this.creditCount = creditCount;
        this.preclusions = preclusions;
        this.prerequisites = prerequisites;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public ModuleType[] getPossibleTypes() {
        return possibleTypes;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public Module[] getPreclusions() {
        return preclusions;
    }

    public Module[] getPrerequisites() {
        return prerequisites;
    }
}
