package seedu.planner.model.course;

/**
 * Enum to describe the programme requirement of a module.
 */
public enum ProgrammeRequirement {
    FOUNDATION("Foundation"),
    MATHEMATICS("Mathematics"),
    SCIENCE("Science"),
    IT_PROFESSIONALISM("IT Professionalism"),
    BREATH_AND_DEPTH("Breath and Depth"),
    INDUSTRIAL_EXPERIENCE_REQUIREMENT("Industrial Experience Requirement");

    private String name;

    ProgrammeRequirement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
