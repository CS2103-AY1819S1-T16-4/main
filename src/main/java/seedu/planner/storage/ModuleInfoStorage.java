package seedu.planner.storage;

import java.util.List;
import java.util.Map;

import seedu.planner.model.ModuleInfo;
import seedu.planner.model.enumeration.ModuleType;

//@@author GabrielYik

/**
 * Represents a storage of {@code ModuleInfo} objects.
 */
public class ModuleInfoStorage {
    //TODO
    // Underlying structure could be a LinkedHashMap
    private Map<ModuleType, List<ModuleInfo>> moduleInfos;

    //TODO
    /**
     * Returns module information of a certain type that {@code ModuleInfoStorage} stores.
     *
     * @param moduleType Type of module.
     * @return Specified type of module information.
     */
    public List<ModuleInfo> getModuleInfos(ModuleType moduleType) {
        return moduleInfos.get(moduleType);
    }
}
