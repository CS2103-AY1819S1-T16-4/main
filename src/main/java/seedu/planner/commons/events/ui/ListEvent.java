package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;

/**
 * An event to display list of modules taken in response to list command.
 */
public class ListEvent extends BaseEvent {
    // indicates that user requested to see list of taken modules for all years
    public static final int ALL_YEARS = -1;

    private final int year;

    public ListEvent(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
