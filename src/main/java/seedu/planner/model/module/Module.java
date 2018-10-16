package seedu.planner.model.module;

//@@author Hilda-Ang //@@author GabrielYik

import com.google.common.collect.ImmutableList;

/**
 * Temporary {@code Module} class placeholder.
 */
public class Module {

    private ModuleType type;

    private ModuleInfo information;

    /**
     * Creates a new {@code Module}.
     * This {@code Module} does not have all its
     * attributes initialised and is meant to be used
     * as a lightweight and convenient object.
     *
     * @param code The {@code Module} code
     */
    public Module(String code) {
        information = new ModuleInfo(code);
    }

    /**
     * Creates a {@code Module}.
     *
     * @param type The {@code ModuleType}
     * @param information The {@code ModuleInfo}
     */
    public Module(ModuleType type, ModuleInfo information) {
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
    public float getCreditCount() {
        return information.getCreditCount();
    }

    /**
     * Gets the {@code Module} preclusions.
     *
     * @return The {@code Module} preclusions
     */
    public ImmutableList<ModuleInfo> getPreclusions() {
        return information.getPreclusions();
    }

    /**
     * Gets the {@code Module} prerequisites.
     *
     * @return The {@code Module} prerequisites
     */
    public ImmutableList<ModuleInfo> getPrerequisites() {
        return information.getPrerequisites();
    }
}
