package seedu.planner.logic.commands;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.events.ui.ShowHelpRequestEvent;
import seedu.planner.commons.events.ui.TabSwitchEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;

public class GoToCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes from one tab to another.\n"
            + "Example: " + COMMAND_WORD + "y1s1";

    public static final String SHOWING_GOTO_MESSAGE = "Go to %1$s";

    private final int tabIndex;
    private final String tabName;

    public GoToCommand(String tabName, int tabIndex) {
        this.tabName = tabName;
        this.tabIndex = tabIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        EventsCenter.getInstance().post(new TabSwitchEvent(tabIndex));
        return new CommandResult(String.format(SHOWING_GOTO_MESSAGE, tabName));
    }
}
