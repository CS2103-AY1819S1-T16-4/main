package seedu.planner.commons.events.ui;

import javafx.collections.ObservableList;

import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.module.Module;

/**
 * An event to display list of modules available in response to suggest command.
 */
public class SuggestEvent extends BaseEvent {
    private final ObservableList<Module> moduleList;

    private final int index;

    public SuggestEvent(ObservableList<Module> moduleList, int index) {
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
