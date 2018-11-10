package seedu.planner.commons.events.ui;

import javafx.collections.ObservableList;

import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.module.Module;

/**
 * An event to display information of a module in response to
 * the {@code Suggest} command.
 */
public class SuggestModulesEvent extends BaseEvent {
    private final ObservableList<Module> moduleList;

    private final int index;

    public SuggestModulesEvent(ObservableList<Module> moduleList, int index) {
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
