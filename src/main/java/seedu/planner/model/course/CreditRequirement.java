package seedu.planner.model.course;

/**
 * Enum to store the amount of credits need to achieved for each Degree Requirement.
 */
public enum CreditRequirement {
    UNIVERSITY_LEVEL_REQUIREMENTS(20),
    FOUNDATION(36),
    MATHEMATICS(12),
    SCIENCE(4),
    IT_PROFESSIONALISM(12),
    INDUSTRIAL_EXPERIENCE_REQUIREMENT(12),
    TEAM_PROJECT(8),
    BREADTH_AND_DEPTH(12);

    private int required;

    CreditRequirement(int required) { this.required = required; }

    public int getRequired() { return required; }
}
