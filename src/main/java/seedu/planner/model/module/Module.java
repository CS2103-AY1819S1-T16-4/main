package seedu.planner.model.module;

//@@author Hilda-Ang //@@author GabrielYik

import java.util.Objects;

import com.google.common.collect.ImmutableList;

/**
 * Temporary {@code Module} class placeholder.
 */
public class Module implements Comparable<Module> {

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
        information = ModuleInfo.getFromModuleCode(code).orElse(new ModuleInfo(code));
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
     * Gets the (@code Module) information.
     *
     * @return the (@code Module) information.
     */
    public ModuleInfo getInfo() {
        return information;
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
     * Gets the {@code Module} description.
     */
    public String getDescription() {
        return information.getDescription();
    }

    /**
     * Gets the {@code ModuleType}.
     *
     * @return The {@code ModuleType}
     */
    public ModuleType getType() {
        return ModuleType.PROGRAMME_REQUIREMENTS;
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

    @Override
    public int compareTo(Module other) {
        return getCode().compareTo(other.getCode());
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module module = (Module) other;
        return information.equals(module.information);
    }

    @Override
    public String toString() {
        return getCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, information);
    }
}
