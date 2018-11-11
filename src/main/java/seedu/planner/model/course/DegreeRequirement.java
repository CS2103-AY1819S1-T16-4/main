package seedu.planner.model.course;

/**
 * Enum to describe the programme requirement of a module.
 */
public enum DegreeRequirement {
    UNIVERSITY_LEVEL_REQUIREMENTS("University Level Requirements"),
    FOUNDATION("Foundation"),
    MATHEMATICS("Mathematics"),
    SCIENCE("Science"),
    IT_PROFESSIONALISM("IT Professionalism"),
    INDUSTRIAL_EXPERIENCE_REQUIREMENT("Industrial Experience Requirement"),
    TEAM_PROJECT("Team Project"),
    FOCUS_AREA_REQUIREMENTS("Focus Area Requirements"),
    BREATH_AND_DEPTH("Breadth and Depth");

    private String name;

    DegreeRequirement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
