package seedu.planner.ui;

import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * A ui for the find module panel that is displayed
 * on the right of the application.
 * This panel corresponds to the {@code find} command.
 */
public class FindModulePanel extends ModuleDescription {
    private static final String FXML = "FindModulePanel.fxml";

    public FindModulePanel(Module module) {
        super(module, FXML);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindModulePanel)) {
            return false;
        }

        // state check
        FindModulePanel panel = (FindModulePanel) other;
        return module.equals(panel.module);
    }
}
