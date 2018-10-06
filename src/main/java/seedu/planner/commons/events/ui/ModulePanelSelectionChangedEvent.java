package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * Represents a selection change in the Module List Panel.
 */
public class ModulePanelSelectionChangedEvent extends BaseEvent {
    private final Module newSelection;

    public ModulePanelSelectionChangedEvent(Module newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Module getNewSelection() {
        return newSelection;
    }
}
