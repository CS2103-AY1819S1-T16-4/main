package seedu.planner.model.module;

//@@author GabrielYik

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

    ModuleType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
