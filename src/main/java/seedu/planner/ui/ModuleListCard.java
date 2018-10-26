package seedu.planner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;

//@@author GabrielYik

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleListCard extends ModuleDescription {

    private static final String FXML = "ModuleListCard.fxml";

    public ModuleListCard(Module module) {
        super(module, FXML);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleListCard)) {
            return false;
        }

        // state check
        ModuleListCard card = (ModuleListCard) other;
        return module.equals(card.module);
    }
}
