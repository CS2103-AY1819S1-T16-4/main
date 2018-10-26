package seedu.planner.commons.events.ui;

/**
 * An event to display the progress of the user in response to
 * the {@code Status} command.
 */
public class StatusEvent {

    public StatusEvent() {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
