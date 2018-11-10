package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableMap;
import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.events.ui.StatusEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.course.DegreeRequirement;

/**
 * Display the credit count status of the user in the planner
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the progress of"
            + "the user and the required credit"
            + "in each degree requirements\n"
            + "Example: University Level Requirement: 8/20\n"
            + "Foundation: 4/36...";

    public static final String MESSAGE_SUCCESS = "Status displayed";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        ObservableMap<DegreeRequirement, int[]> statusMap = model.getStatus();

        EventsCenter.getInstance().post(new StatusEvent(statusMap));
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
