package seedu.planner.commons.events.ui;

import javafx.collections.ObservableMap;
import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.course.DegreeRequirement;

/**
 * An event to display the progress of the user in response to
 * the {@code Status} command.
 */
public class StatusEvent extends BaseEvent {

    private ObservableMap<DegreeRequirement, int[]> statusMap;

    public StatusEvent(ObservableMap<DegreeRequirement, int[]> statusMap) {
        this.statusMap = statusMap;
    }

    public ObservableMap<DegreeRequirement, int[]> getStatusMessage() { return statusMap; }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
