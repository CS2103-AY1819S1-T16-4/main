package seedu.planner.commons.events.ui;

import javafx.collections.ObservableList;

import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.module.Module;

/**
 * An event to display information of a module in response to
 * the {@code Suggest} command.
 */
public class SuggestModuleEvent extends BaseEvent {
    private final ObservableList<Module> moduleList;

    public SuggestModuleEvent(ObservableList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public ObservableList<Module> getModuleList() {
        return moduleList;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
