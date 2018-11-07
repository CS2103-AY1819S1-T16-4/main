package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.events.ui.StatusEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.course.ProgrammeRequirement;
import seedu.planner.model.module.Module;

import java.util.Map;

/**
 * Display the credit count status of the user in the planner
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";
    private static final String CREDITS_LEFT = "Total credits left to fulfill course requirement: ";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows total credit achieved "
            + "in each semester based on"
            + "existing modules in the planner.\n"
            + "Example: " + "Semester 1: 20\n"
            + "Semester 2: 24\n"
            + CREDITS_LEFT + 116;


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        Map<ProgrammeRequirement, int[]> statusMap = model.status();
        StringBuilder sb = new StringBuilder();
        for (ProgrammeRequirement pr : ProgrammeRequirement.values()) {
            sb.append(pr.toString());
            sb.append(": ");
            sb.append(statusMap.get(pr)[0]);
            sb.append("\n");
        }

        EventsCenter.getInstance().post(new StatusEvent());
        return new CommandResult(sb.toString().trim());
    }

}
