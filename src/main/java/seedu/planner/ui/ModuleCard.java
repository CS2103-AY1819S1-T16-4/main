package seedu.planner.ui;

import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends ModuleDescription {

    private static final String FXML = "ModuleCard.fxml";

    public ModuleCard(Module module) {
        super(module, FXML);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return module.equals(card.module);
    }
}
