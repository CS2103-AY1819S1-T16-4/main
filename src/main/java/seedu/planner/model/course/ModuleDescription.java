package seedu.planner.model.course;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to describe properties of a Module when taken by a Major.
 * See also {@link ModuleDescription}.
 */
public class ModuleDescription {
    private String code;
    private DegreeRequirement requirement;
    private List<FocusArea> focusAreas;

    /**
     * Default constructor for JSON deserialization.
     */
    public ModuleDescription() {
        focusAreas = new ArrayList<>();
    }

    public ModuleDescription(String code, DegreeRequirement requirement) {
        this(code, requirement, new ArrayList<>());
    }

    public ModuleDescription(String code, DegreeRequirement requirement, List<FocusArea> focusAreas) {
        this.code = code;
        this.requirement = requirement;
        this.focusAreas = focusAreas;
    }

    public String getCode() {
        return code;
    }

    public DegreeRequirement getRequirement() {
        return requirement;
    }

    public List<FocusArea> getFocusAreas() {
        return focusAreas;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleDescription)) {
            return false;
        }

        ModuleDescription otherModule = (ModuleDescription) other;
        return code.equals(otherModule.code)
            && requirement.equals(otherModule.requirement)
            && focusAreas.equals(otherModule.focusAreas);
    }
}
