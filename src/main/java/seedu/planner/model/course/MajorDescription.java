package seedu.planner.model.course;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;

import seedu.planner.MainApp;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.util.JsonUtil;

/**
 * Class to describe requirements of a Major.
 */
public class MajorDescription {
    public static final TypeReference<HashMap<Major, MajorDescription>> MAP_TYPE_REF = new TypeReference<>() {};

    private static final String DEFAULT_FILE_PATH = "/data/majorDescription.json";

    private static final MajorDescriptionRetriever instance = new MajorDescriptionRetriever(DEFAULT_FILE_PATH);

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

    /**
     * Class to retrieve mapping of major to module descriptions in JSON file.
     * Adapted from seedu.planner.model.ModuleInfo.ModuleInfoRetriever.
     */
    private static class MajorDescriptionRetriever {
        private static Logger logger = LogsCenter.getLogger(MajorDescriptionRetriever.class);

        private ImmutableMap<Major, MajorDescription> majorToMajorDescriptionMap;
        private MajorDescriptionRetriever(String path) {
            try {
                URL resource = MainApp.class.getResource(path);
                String text = Resources.toString(resource, Charsets.UTF_8);
                Map<Major, MajorDescription> mutableMap = JsonUtil.getObjectMapper().readValue(text, MAP_TYPE_REF);
                majorToMajorDescriptionMap = ImmutableMap.copyOf(mutableMap);
            } catch (IOException e) {
                logger.warning("Unable to read majorDescription file. Start with an empty map.");
                ImmutableMap.Builder<Major, MajorDescription> builder = ImmutableMap.builder();
                majorToMajorDescriptionMap = builder.build();
            }
        }

        public Optional<MajorDescription> getFromMajor(Major major) {
            if (majorToMajorDescriptionMap.containsKey(major)) {
                return Optional.of(majorToMajorDescriptionMap.get(major));
            }
            return Optional.empty();
        }
    }

    public Major getMajor() {
        return major;
    }

    /**
     * Returns a list of {@code ModuleDescription} pre-sorted according to decreasing importance.
     */
    public List<ModuleDescription> getModules() {
        return modules;
    }

    /**
     * Returns a list of module code prefixes for this major.
     */
    public List<String> getPrefixes() {
        return facultyModuleCodePrefixes;
    }

    public static Optional<MajorDescription> getFromMajor(Major major) {
        return instance.getFromMajor(major);
    }

    public static Optional<ModuleDescription> getModuleCode(Major major, String code) {
        Optional<MajorDescription> optMajorDescription = MajorDescription.getFromMajor(major);
        if (optMajorDescription.isPresent()) {
            List<ModuleDescription> listModuleDescription = optMajorDescription.get().getModules();
            for (ModuleDescription moduleDescription: listModuleDescription) {
                if (moduleDescription.getCode().equals(code)) {
                    return Optional.of(moduleDescription);
                }
            }
        }
        return Optional.empty();
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
