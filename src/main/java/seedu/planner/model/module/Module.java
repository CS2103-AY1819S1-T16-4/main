package seedu.planner.model.module;

//@@author Hilda-Ang //@@author GabrielYik

/**
 * Temporary {@code Module} class placeholder.
 */
public class Module {

    private ModuleType type;

    private ModuleInformation information;

    /**
     * Creates a {@code Module}.
     *
     * @param type The {@code ModuleType}
     * @param information The {@code ModuleInformation}
     */
    public Module(ModuleType type, ModuleInformation information) {
        this.type = type;
        this.information = information;
    }

    /**
     * Gets the {@code Module} code.
     *
     * @return The {@code Module} code
     */
    public String getCode() {
        return information.getCode();
    }

    /**
     * Gets the {@code Module} name.
     *
     * @return The {@code Module} name
     */
    public String getName() {
        return information.getName();
    }

    /**
     * Gets the {@code ModuleType}.
     *
     * @return The {@code ModuleType}
     */
    public ModuleType getType() {
        return type;
    }

    /**
     * Gets the possible {@code ModuleType}s.
     *
     * @return The possible {@code ModuleType}s
     */
    public ModuleType[] getPossibleTypes() {
        return information.getPossibleTypes();
    }

    /**
     * Gets the {@code Module} credit count.
     *
     * @return The {@code Module} credit count
     */
    public int getCreditCount() {
        return information.getCreditCount();
    }

    /**
     * Gets the {@code Module} preclusions.
     *
     * @return The {@code Module} preclusions
     */
    public Module[] getPreclusions() {
        return information.getPreclusions();
    }

    /**
     * Gets the {@code Module} prerequisites.
     *
     * @return The {@code Module} prerequisites
     */
    public Module[] getPrerequisites() {
        return information.getPrerequisites();
    }
}
