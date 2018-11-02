package seedu.planner.ui;

public enum ModulePanelType {
    TAKEN("Taken"),
    SUGGESTED("Suggested");

    private String name;

    ModulePanelType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
