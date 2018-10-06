package seedu.planner.model.module;

//@@author Hilda-Ang

/**
 * Temporary {@code Module} class placeholder.
 */
public class Module {

    private ModuleType type;

    private ModuleInformation information;

    public Module(ModuleType type, ModuleInformation information) {
        this.type = type;
        this.information = information;
    }

    public String getCode() {
        return information.getCode();
    }

    public String getName() {
        return information.getName();
    }

    public ModuleType getType() {
        return type;
    }

    public ModuleType[] getPossibleTypes() {
        return information.getPossibleTypes();
    }

    public int getCreditCount() {
        return information.getCreditCount();
    }

    public Module[] getPreclusions() {
        return information.getPreclusions();
    }

    public Module[] getPrerequisites() {
        return information.getPrerequisites();
    }

}
