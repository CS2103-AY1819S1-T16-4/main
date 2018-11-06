package seedu.planner.model.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Class to describe requirements of a Major.
 */
public class MajorDescription {
    public static final TypeReference<HashMap<Major, MajorDescription>> mapTypeRef = new TypeReference<>() {};

    private Major major;
    private List<ModuleDescription> modules;
    private List<String> facultyModuleCodePrefixes;

    /**
     * Default constructor for JSON deserialization.
     */
    public MajorDescription() {
        modules = new ArrayList<>();
        facultyModuleCodePrefixes = new ArrayList<>();
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

    public List<String> getPrefixes() {
        return facultyModuleCodePrefixes;
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
