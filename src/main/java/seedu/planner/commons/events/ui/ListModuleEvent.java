package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;

/**
 * An event to display list of modules taken in response to {@code List} command.
 */
public class ListModuleEvent extends BaseEvent {
    private final int index;

    public ListModuleEvent(int index) {
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
