package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.events.ui.StatusEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.course.CreditRequirement;
import seedu.planner.model.course.DegreeRequirement;


/**
 * Display the credit count status of the user in the planner
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows total credit achieved "
            + "in each programme requirements\n"
            + "Example: " + "Foundation: 20 (need 16 more)\n";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        Map<DegreeRequirement, int[]> statusMap = model.status();
        CreditRequirement[] cr = CreditRequirement.values();
        DegreeRequirement[] dr = DegreeRequirement.values();
        int[] need = new int[cr.length];
        for (int i = 0; i < cr.length - 1; i++) {
            need[i] = Math.max(0, cr[i].getRequired() - statusMap.get(dr[i])[0]);
        }
        int requiredFocusArea = model.numberOfFocusAreas() * cr[cr.length - 1].getRequired();
        need[cr.length - 1] = Math.max(0, requiredFocusArea - statusMap.get(dr[dr.length - 1])[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cr.length; i++) {
            if (i == cr.length - 1) {
                sb.append(dr[i - 1].toString());
            } else {
                sb.append(dr[i].toString());
            }
            if (i == cr.length - 2) {
                sb.append(" (Team Project)");
            }
            if (i == cr.length - 1) {
                sb.append(" (Focus Areas)");
            }
            sb.append(": ");
            if (i == cr.length - 1) {
                sb.append(statusMap.get(dr[i - 1])[1]);
            } else {
                sb.append(statusMap.get(dr[i])[0]);
            }
            sb.append(" (need ");
            sb.append(need[i]);
            sb.append(" more)\n");
        }

        EventsCenter.getInstance().post(new StatusEvent());
        return new CommandResult(sb.toString().trim());
    }

}
