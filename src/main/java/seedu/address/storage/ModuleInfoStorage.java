package seedu.address.storage;

import java.util.List;

import seedu.address.model.ModuleInfo;
import seedu.address.model.enumeration.ModuleType;

//@@author GabrielYik

/**
 * Represents a storage of {@code ModuleInfo} objects.
 */
public class ModuleInfoStorage {
    private List<ModuleInfo> moduleInfos;

    //TODO
    /**
     * Returns all the module information that {@code ModuleInfoStorage} stores.
     *
     * @return All module information.
     */
    public List<ModuleInfo> getModuleInfos() {
        return moduleInfos;
    }

    //TODO

    /**
     * Returns module information of a certain type that {@code ModuleInfoStorage} stores.
     *
     * @param moduleType Type of module.
     * @return Specified type of module information.
     */
    public List<ModuleInfo> getModuleInfos(ModuleType moduleType) {
        return moduleInfos;
    }
}
