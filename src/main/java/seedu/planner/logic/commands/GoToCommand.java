package seedu.planner.logic.commands;

import seedu.planner.commons.core.EventsCenter;
import seedu.planner.commons.events.ui.TabSwitchEvent;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;

//@@author GabrielYik

/**
 * A class representing the {@code goto} command.
 */
public class GoToCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes from one tab to another.\n"
            + "Parameters: TABNAME\n"
            + "Example: " + COMMAND_WORD + "y1s1";

    public static final String SHOWING_GOTO_MESSAGE = "Go to %1$s";

    private final int tabIndex;
    private final String tabName;

    /**
     * Constructs a {@code GoToCommand}.
     * The {@code tabName} refers to the name seen on the tab in the ui. For e.g. Y1S2.
     * The {@code tabIndex} refers to the zero-based index of the tab in the ui.
     * For e.g. the leftmost tab, which name is Y1S1, has an index of 0.
     *
     * @param tabName The name of the tab
     * @param tabIndex The index of the tab
     */
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
