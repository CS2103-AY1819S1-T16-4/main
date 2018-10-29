package seedu.planner.model.module;

//@@author GabrielYik

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;

import seedu.planner.MainApp;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.util.JsonUtil;

/**
 * Represents an immutable {@code ModuleInfo} class.
 * <p>
 * REQUIRES: Module code is globally unique, no two ModuleInfo object has the same module code.
 */
public class ModuleInfo {
    public static final String MESSAGE_MODULE_CODE_CONSTRAINTS = "Module codes should be of the format VW(X)1234(YZ).\n"
            + "VW refers to a prefix like CS or CG that describes the type of the module.\n"
            + "(X) refers to an optional prefix add-on.\n"
            + "1234 refers to a sequence of positive numbers.\n"
            + "(YZ) refers to an optional postfix.";

    // @@author rongjiecomputer

    private static final String DEFAULT_FILE_PATH = "/data/moduleInfo.json";

    // An object that is initialized from JSON file and act like a const data.
    private static final ModuleInfoRetriever instance = new ModuleInfoRetriever(DEFAULT_FILE_PATH);

    /**
     * Class to retrieve {@code ModuleInfo} from JSON file packaged in JAR file.
     */
    private static class ModuleInfoRetriever {
        private static Logger logger = LogsCenter.getLogger(ModuleInfoRetriever.class);

        private ImmutableMap<String, ModuleInfo> codeToModuleInfoMap;
        private ModuleInfo[] moduleInfoList;

        ModuleInfoRetriever(String path) {
            try {
                URL resource = MainApp.class.getResource(path);
                String text = Resources.toString(resource, Charsets.UTF_8);

                moduleInfoList = JsonUtil.fromJsonString(text, ModuleInfo[].class);

                moduleInfoList = finalizeModuleInfo(moduleInfoList);
            } catch (IOException e) {
                logger.warning("Problem while reading from resource file. "
                    + "Will be starting with an empty module database");
                moduleInfoList = new ModuleInfo[] {};
            }
        }

        /**
         *  Map module code to {@code ModuleInfo}.
         *
         * @param moduleCode Module code
         */
        public Optional<ModuleInfo> getFromModuleCode(String moduleCode) {
            if (codeToModuleInfoMap.containsKey(moduleCode)) {
                return Optional.<ModuleInfo>of(codeToModuleInfoMap.get(moduleCode));
            }
            return Optional.empty();
        }

        /**
         * Takes in a list of {@code ModuleInfo}s deserialzied by JSON parser and
         * finalize {@code ModuleInfo}s' internal structure.
         *
         * @param moduleInfo List of {@code ModuleInfo}s deserialized by JSON parser.
         */
        public ModuleInfo[] finalizeModuleInfo(ModuleInfo[] moduleInfo) {
            ImmutableMap.Builder<String, ModuleInfo> builder = ImmutableMap.builder();
            for (ModuleInfo mInfo : moduleInfo) {
                builder.put(mInfo.getCode(), mInfo);
            }

            codeToModuleInfoMap = builder.build();

            for (ModuleInfo mInfo : moduleInfo) {
                mInfo.finalize(codeToModuleInfoMap);
            }
            return moduleInfo;
        }
    }

    // @@author

    private String code;

    private String name;

    private ModuleType[] possibleTypes;

    /**
     * Module credit. We set the type to be float because some modules have 0.5 MC.
     */
    private float creditCount;

    @JsonProperty("preclusions")
    private String[] preclusions;

    @JsonProperty("prerequisites")
    private String[] prerequisites;

    @JsonIgnore
    private ImmutableList<ModuleInfo> precluModuleInfo;

    @JsonIgnore
    private ImmutableList<ModuleInfo> prereqModuleInfo;

    private boolean finalized = false;

    /**
     * Default constructor required by JSON parser.
     */
    public ModuleInfo() {
    }

    /**
     * Creates a new {@code ModuleInfo}.
     * This {@code ModuleInfo} does not have all its
     * attributes initialised and is meant to be used
     * as a lightweight and convenient object.
     *
     * @param code The {@code Module} code
     */
    public ModuleInfo(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public ModuleType[] getPossibleTypes() {
        return possibleTypes;
    }

    public float getCreditCount() {
        return creditCount;
    }

    // @@author rongjiecomputer
    public ImmutableList<ModuleInfo> getPrerequisites() {
        Preconditions.checkState(finalized);
        return prereqModuleInfo;
    }

    public ImmutableList<ModuleInfo> getPreclusions() {
        Preconditions.checkState(finalized);
        return precluModuleInfo;
    }

    /**
     * Initialize internal lists of prerequisite and preclusion {@code ModuleInfo}.
     *
     * @param map An immutable map that maps module code to {@code ModuleInfo}.
     */
    private void finalize(ImmutableMap<String, ModuleInfo> map) {
        Preconditions.checkState(!finalized);

        prereqModuleInfo = Arrays.stream(prerequisites)
                .map(code -> map.get(code))
                .filter(mInfo -> mInfo != null)
                .collect(ImmutableList.toImmutableList());
        precluModuleInfo = Arrays.stream(preclusions)
                .map(code -> map.get(code))
                .filter(mInfo -> mInfo != null)
                .collect(ImmutableList.toImmutableList());

        finalized = true;
    }

    public static ModuleInfo[] getModuleInfoList() {
        return instance.moduleInfoList;
    }

    /**
     *  Map module code to {@code ModuleInfo}.
     *
     * @param moduleCode Module code
     */
    public static Optional<ModuleInfo> getFromModuleCode(String moduleCode) {
        return instance.getFromModuleCode(moduleCode);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleInfo)) {
            return false;
        }

        ModuleInfo otherModuleInfo = (ModuleInfo) other;

        // state check
        return code.equals(otherModuleInfo.code);
    }

    @Override
    public String toString() {
        String str = "ModuleInfo(" + code + ") {";
        if (prereqModuleInfo.size() > 0) {
            str += " prereq:";
            for (ModuleInfo prereq : prereqModuleInfo) {
                str += " " + prereq.code;
            }
        }
        if (precluModuleInfo.size() > 0) {
            str += " preclu:";
            for (ModuleInfo preclu : precluModuleInfo) {
                str += " " + preclu.code;
            }
        }
        return str + " }";
    }
    // @@author
}
