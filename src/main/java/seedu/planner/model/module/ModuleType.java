package seedu.planner.model.module;

//@@author GabrielYik

import seedu.planner.commons.exceptions.IllegalValueException;

/**
 * Represents the degree requirements of a Computer Science programme.
 */
public enum ModuleType {
    UNIVERSITY_LEVEL_REQUIREMENTS("University Level Requirements"),
    UNRESTRICTED_ELECTIVES("Unrestricted Electives"),
    PROGRAMME_REQUIREMENTS("Programme Requirements"),
    PR_FOUNDATION("PR Foundation"),
    PR_BREADTH_AND_DEPTH("PR Breadth and Depth"),
    PR_INDUSTRIAL_EXPERIENCE("PR Industrial Experience"),
    PR_IT_PROFESSIONALISM("PR IT Professionalism"),
    PR_MATHEMATICS_AND_SCIENCE("PR Mathematics and Science");

    private final String name;
    private static final String MESSAGE_UNKNOWN_MODULE_TYPE = "Not a known module type.";

    ModuleType(String name) {
        this.name = name;
    }

    public static ModuleType fromString(String name) throws IllegalValueException {
        for (ModuleType type : ModuleType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalValueException(MESSAGE_UNKNOWN_MODULE_TYPE);
    }

    @Override
    public String toString() {
        return name;
    }
}
