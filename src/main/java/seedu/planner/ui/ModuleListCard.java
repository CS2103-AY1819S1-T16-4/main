package seedu.planner.ui;

import seedu.planner.model.module.Module;

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
