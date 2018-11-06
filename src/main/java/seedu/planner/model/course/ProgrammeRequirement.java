package seedu.planner.model.course;

public enum ProgrammeRequirement {
    FOUNDATION("Foundation"),
    MATHEMATICS("Mathematics"),
    SCIENCE("Science"),
    IT_PROFESSIONALISM("IT Professionalism"),
    BREATH_AND_DEPTH("Breath and Depth"),
    INDUSTRIAL_EXPERIENCE_REQUIREMENT("Industrial Experience Requirement");

    private String name;

    private ProgrammeRequirement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
