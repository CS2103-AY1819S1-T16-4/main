package seedu.planner.commons.events.model;

import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.ReadOnlyModulePlanner;

/**
 * Indicates the ModulePlanner in the model has changed.
 */
public class ModulePlannerChangedEvent extends BaseEvent {

    public final ReadOnlyModulePlanner data;

    public ModulePlannerChangedEvent(ReadOnlyModulePlanner data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of persons ";
    }
}
