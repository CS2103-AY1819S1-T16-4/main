package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;

/**
 * An event to switch tabs in the ui.
 */
public class TabSwitchEvent extends BaseEvent {

    private final int index;

    public TabSwitchEvent(int index) {
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
