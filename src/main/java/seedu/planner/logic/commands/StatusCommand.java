package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.events.ui.StatusEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;


/**
 * Display the credit count status of the user in the planner
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows total credit achieved "
            + "in each programme requirements\n"
            + "Example: " + "Foundation: 20 (need 16 more)\n";

    private Map<String, Integer> required = new HashMap<>();

    /**
     * Map the required credit for each course requirements.
     */
    private void putRequired(Map<String, Integer> map) {
        Set<String> programmeRequirements = map.keySet();
        if (programmeRequirements.contains("Foundation")) {
            Iterator<String> iter = programmeRequirements.iterator();
            required.put(iter.next(), 20);
            required.put(iter.next(), 36);
            required.put(iter.next(), 12);
            required.put(iter.next(), 4);
            required.put(iter.next(), 12);
            required.put(iter.next(), 12);
            required.put(iter.next(), 16);
            while (iter.hasNext()) {
                required.put(iter.next(), 12);
            }
        }
    }
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        Map<String, Integer> statusMap = model.status();
        putRequired(statusMap);
        StringBuilder sb = new StringBuilder();
        for (String programmeRequirements : statusMap.keySet()) {
            sb.append(programmeRequirements);
            sb.append(": ");
            sb.append(statusMap.get(programmeRequirements));
            sb.append(" (need ");
            sb.append(Math.max(0, required.get(programmeRequirements) - statusMap.get(programmeRequirements)));
            sb.append(" more)");
            sb.append("\n");
        }

        EventsCenter.getInstance().post(new StatusEvent());
        return new CommandResult(sb.toString().trim());
    }

}
