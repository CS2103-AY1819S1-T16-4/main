package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.ModulePlanner;
import seedu.planner.model.module.Module;

/**
 * Display the credit count status of the user in the planner
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";
    private static final String CREDITS_LEFT = "Total credits left to fulfill course requirement: ";

    private static final int CREDIT_TO_GRADUATE = 160;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows total credit achieved "
            + "in each semester based on"
            + "existing modules in the planner.\n"
            + "Example: " + "Semester 1: 20\n"
            + "Semester 2: 24\n"
            + CREDITS_LEFT + 116;

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        int[] creditCounts = new int[ModulePlanner.MAX_NUMBER_SEMESTERS];
        int totalCreditCount = 0;
        for (int i = 0; i < ModulePlanner.MAX_NUMBER_SEMESTERS; i++) {
            for (Module module : model.getTakenModuleList(i)) {
                creditCounts[i] += module.getCreditCount();
            }
            totalCreditCount += creditCounts[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= ModulePlanner.MAX_NUMBER_SEMESTERS; i++) {
            sb.append("Semester " + i + ": " + creditCounts[i - 1] + "\n");
        }
        sb.append(CREDITS_LEFT + (CREDIT_TO_GRADUATE - totalCreditCount));

        return new CommandResult(sb.toString().trim());
    }

}
