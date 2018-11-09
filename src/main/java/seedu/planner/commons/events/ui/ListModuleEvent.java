package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;

/**
 * An event to display list of modules taken in response to {@code List} command.
 */
public class ListModuleEvent extends BaseEvent {
    public static final int ALL_YEARS = -1;

    private final int year;

    public ListModuleEvent(int year) {
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
