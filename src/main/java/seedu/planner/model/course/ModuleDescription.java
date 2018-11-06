package seedu.planner.model.course;

import java.util.ArrayList;
import java.util.List;

public class ModuleDescription {
    private String name;
    private ProgrammeRequirement requirement;
    private List<FocusArea> focusAreas;

    /**
     * Default constructor for JSON deserialization.
     */
    public ModuleDescription() {
        focusAreas = new ArrayList<>();
    }

    public ModuleDescription(String name, ProgrammeRequirement requirement) {
        this(name, requirement, new ArrayList<>());
    }

    public ModuleDescription(String name, ProgrammeRequirement requirement, List<FocusArea> focusAreas) {
        this.name = name;
        this.requirement = requirement;
        this.focusAreas = focusAreas;
    }

    public String getName() {
        return name;
    }

    public ProgrammeRequirement getRequirement() {
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
        return name.equals(otherModule.name)
            && requirement.equals(otherModule.requirement)
            && focusAreas.equals(otherModule.focusAreas);
    }
}
