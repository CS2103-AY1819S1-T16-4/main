package seedu.planner.model.module;

//@@author GabrielYik @@author rongjiecomputer

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Represents an immutable {@code ModuleInfo} class.
 * <p>
 * REQUIRES: Module code is globally unique, no two ModuleInfo object has the same module code.
 */
public class ModuleInfo {
    public static final String MESSAGE_MODULE_CODE_CONSTRAINTS = "Module codes should be of the format WXY1234(Z), "
            + "where WXY refers to a prefix like CS or IS that describes the type of the module, "
            + "1234 refers to a sequence of positive numbers, "
            + "and (Z) refers to an optional postfix.";

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

    /**
     * Creates a new {@code ModuleInfo}. For testing only.
     *
     * @param code          The {@code Module} code
     * @param name          The {@code Module} name
     * @param possibleTypes The possible {@code ModuleType}s
     * @param creditCount   The credit count
     * @param preclusions   An array of preclusion module code strings
     * @param prerequisites An array of prerequisite module code strings
     */
    public ModuleInfo(String code, String name, ModuleType[] possibleTypes,
                      float creditCount, String[] preclusions, String[] prerequisites) {
        this.code = code;
        this.name = name;
        this.possibleTypes = possibleTypes;
        this.creditCount = creditCount;
        this.preclusions = preclusions;
        this.prerequisites = prerequisites;
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
    public void finalize(ImmutableMap<String, ModuleInfo> map) {
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

    /**
     * Takes in a list of {@code ModuleInfo}s deserialzied by JSON parser and
     * finalize {@code ModuleInfo}s' internal structure.
     *
     * @param moduleInfo List of {@code ModuleInfo}s deserialized by JSON parser.
     */
    public static ModuleInfo[] finalizeModuleInfo(ModuleInfo[] moduleInfo) {
        ImmutableMap.Builder<String, ModuleInfo> builder = ImmutableMap.builder();
        for (ModuleInfo mInfo : moduleInfo) {
            builder.put(mInfo.getCode(), mInfo);
        }

        // TODO(rongjiecomputer) This code->moduleInfo map is useful for other class too.
        // Figure out a place to expose this to other classes.
        ImmutableMap<String, ModuleInfo> map = builder.build();

        for (ModuleInfo mInfo : moduleInfo) {
            mInfo.finalize(map);
        }
        return moduleInfo;
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
