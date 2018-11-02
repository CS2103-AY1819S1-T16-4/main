package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;

public class AddModuleEvent extends BaseEvent {

    public final int index;

    public AddModuleEvent(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
