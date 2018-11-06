package seedu.planner.model.course;

import java.util.ArrayList;
import java.util.List;

public class MajorDescription {
    private Major major;
    private List<ModuleDescription> modules;

    /**
     * Default constructor for JSON deserialization.
     */
    public MajorDescription() {
        modules = new ArrayList<>();
    }

    public MajorDescription(Major major, List<ModuleDescription> modules) {
        this.major = major;
        this.modules = modules;
    }

    public Major getMajor() {
        return major;
    }

    public List<ModuleDescription> getModules() {
        return modules;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MajorDescription)) {
            return false;
        }

        MajorDescription otherMajor = (MajorDescription) other;
        return major.equals(otherMajor.major) && modules.equals(otherMajor.modules);
    }
}
