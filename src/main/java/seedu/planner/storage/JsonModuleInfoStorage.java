package seedu.planner.storage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.planner.commons.exceptions.DataConversionException;
import seedu.planner.commons.util.JsonUtil;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.module.ModuleType;

//@@author rongjiecomputer
/**
 * A class to access {@link seedu.planner.model.module.ModuleInfo} stored in the hard disk as a json file.
 */
public class JsonModuleInfoStorage implements ModuleInfoStorage {

    private Path filePath;

    public JsonModuleInfoStorage(Path filePath) {
        this.filePath = filePath;
    }


    public Path getModuleInfoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ModuleInfo[]> readModuleInfo() throws DataConversionException {
        Optional<ModuleInfo[]> optionalModuleInfo = JsonUtil.readJsonFile(filePath, ModuleInfo[].class);
        return optionalModuleInfo.map(ModuleInfo::finalizeModuleInfo);
    }

    /**
     * Returns {@code ModuleInfo} of a certain {@code ModuleType} that {@code ModuleInfoStorage} stores.
     *
     * @param moduleType Type of {@code module}
     * @return A list of {@code ModuleInfo} of {@code moduleType}
     */
    @Override
    public List<ModuleInfo> getModuleInfo(ModuleType moduleType) {
        // TODO(rongjiecomputer) Implement filter
        return new ArrayList<>();
    }
}
