package seedu.planner.model.course;

/**
 * Enum to store the amount of credits need to achieved for each Degree Requirement.
 */
public enum CreditRequired {
    UNIVERSITY_LEVEL_REQUIREMENTS(20),
    FOUNDATION(36),
    MATHEMATICS(12),
    SCIENCE(4),
    IT_PROFESSIONALISM(12),
    INDUSTRIAL_EXPERIENCE_REQUIREMENT(12),
    TEAM_PROJECT(8),
    FOCUS_AREA_REQUIREMENT(12);

    private int required;

    CreditRequired(int required) { this.required = required; }

    public int getRequired() { return required; }
}
