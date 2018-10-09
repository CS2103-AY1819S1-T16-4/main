package seedu.planner.storage;

import java.util.List;
import java.util.Map;

import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

//@@author GabrielYik

/**
 * Represents a storage of {@code ModuleInfo} objects.
 */
public class ModuleInformationStorage {
    //TODO
    // Underlying structure could be a LinkedHashMap
    private Map<ModuleType, List<ModuleInfo>> moduleInformations;

    //TODO
    /**
     * Returns {@code ModuleInfo} of a certain {@code ModuleType} that {@code ModuleInfoStorage} stores.
     *
     * @param moduleType Type of module
     * @return A list of {@code ModuleInfo} of {@code moduleType}
     */
    public List<ModuleInfo> getModuleInformation(ModuleType moduleType) {
        return moduleInformations.get(moduleType);
    }
}
