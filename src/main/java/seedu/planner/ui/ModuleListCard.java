package seedu.planner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleListCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    public final Module module;

    @FXML
    private HBox cardPane;

    @FXML
    private Label moduleCode;

    @FXML
    private Label moduleName;

    @FXML
    private Label moduleType;

    @FXML
    private Label creditCount;

    @FXML
    private Label preclusion;

    @FXML
    private FlowPane preclusions;

    @FXML
    private Label prerequisite;

    @FXML
    private FlowPane prerequisites;

    public ModuleListCard(Module module) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getCode());
        moduleName.setText(module.getName());
        moduleType.setText("Fulfils: " + module.getType().toString());
        creditCount.setText("Modular Credits: " + Integer.toString(module.getCreditCount()));

        for (Module m : module.getPreclusions()) {
            preclusions.getChildren().add(new Label(m.getCode()));
        }

        switch(module.getPreclusions().length) {
        case 0:
            preclusion.setText("Preclusion: none");
            break;

        case 1:
            preclusion.setText("Preclusion: ");
            break;

        default:
            preclusion.setText("Preclusions: ");
        }

        for (Module m : module.getPrerequisites()) {
            prerequisites.getChildren().add(new Label(m.getCode()));
        }

        switch(module.getPrerequisites().length) {
        case 0:
            prerequisite.setText("Prerequisite: none");
            break;

        case 1:
            prerequisite.setText("Prerequisite: ");
            break;

        default:
            prerequisite.setText("Prerequisites: ");
        }
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
        return moduleCode.equals(card.moduleCode);
    }
}
