package seedu.planner.commons.events.model;

import java.util.List;

import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.ReadOnlyModulePlanner;
import seedu.planner.model.semester.Semester;

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
        StringBuilder sb = new StringBuilder();
        List<Semester> semesters = data.getSemesters();
        sb.append("\n");
        for (Semester semester : semesters) {
            sb.append(semester.toString() + " : " + semester.getModules().size() + "\n");
        }

        return sb.toString();
    }
}
