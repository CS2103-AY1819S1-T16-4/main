package seedu.planner.storage;

import java.util.List;
import java.util.Map;

import seedu.planner.model.module.ModuleInformation;
import seedu.planner.model.module.ModuleType;

//@@author GabrielYik

/**
 * Represents a storage of {@code ModuleInformation} objects.
 */
public class ModuleInformationStorage {
    //TODO
    // Underlying structure could be a LinkedHashMap
    private Map<ModuleType, List<ModuleInformation>> moduleInformations;

    //TODO
    /**
     * Returns {@code ModuleInformation} of a certain {@code ModuleType} that {@code ModuleInfoStorage} stores.
     *
     * @param moduleType Type of module
     * @return A list of {@code ModuleInformation} of {@code moduleType}
     */
    public List<ModuleInformation> getModuleInformation(ModuleType moduleType) {
        return moduleInformations.get(moduleType);
    }
}
