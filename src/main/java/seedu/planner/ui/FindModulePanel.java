package seedu.planner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;

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
