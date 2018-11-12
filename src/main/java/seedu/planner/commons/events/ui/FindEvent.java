package seedu.planner.commons.events.ui;

import seedu.planner.commons.events.BaseEvent;
import seedu.planner.model.module.Module;

/**
 * An event to display information of a module in response to
 * the {@code Find} command.
 */
public class FindEvent extends BaseEvent {

    private final Module module;

    public FindEvent(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
