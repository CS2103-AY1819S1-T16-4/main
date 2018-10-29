package seedu.planner.model.course;

//@@author GabrielYik

/**
 * Represents the focus areas of computer science students.
 * These focus areas are applicable only to new cohorts from AY2015-16 onwards.
 */
public enum FocusArea {
    ALGORITHMS_AND_THEORY("Algorithms and Theory"),
    ARTIFICIAL_INTELLIGENCE("Artificial Intelligence"),
    COMPUTER_GRAPHICS_AND_GAMES("Computer Graphics and Games"),
    COMPUTER_SECURITY("Computer Security"),
    DATABASE_SYSTEMS("Database Systems"),
    MULTIMEDIA_INFORMATION_RETRIEVAL("Multimedia Information Retrieval"),
    NETWORKING_AND_DISTRIBUTED_SYSTEMS("Networking and Distributed Systems"),
    PARALLEL_COMPUTING("Parallel Computing"),
    PROGRAMMING_LANGUAGES("Programming Languages"),
    SOFTWARE_ENGINEERING("Software Engineering"),
    UNKNOWN("Unknown");

    private final String name;

    FocusArea(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
