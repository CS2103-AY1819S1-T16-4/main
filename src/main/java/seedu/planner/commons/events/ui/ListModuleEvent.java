package seedu.planner.commons.events.ui;

import javafx.collections.ObservableList;
import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.module.Module;

public class ListModuleEvent extends BaseEvent {
    private final ObservableList<Module> moduleList;
    private final int index;

    public ListModuleEvent(ObservableList<Module> moduleList, int index) {
        this.moduleList = moduleList;
        this.index = index;
    }

    public ObservableList<Module> getModuleList() {
        return moduleList;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
