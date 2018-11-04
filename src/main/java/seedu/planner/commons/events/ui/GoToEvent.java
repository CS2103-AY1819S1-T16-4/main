package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;

/**
 * An event to switch the year semester view in the ui.
 */
public class GoToEvent extends BaseEvent {

    private final int index;

    public GoToEvent(int index) {
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
