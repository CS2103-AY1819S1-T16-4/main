package seedu.planner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;

//@@author GabrielYik

//TODO: a lot of similarities with ModuleListCard --> need to abstract out

/**
 * A ui for the find module panel that is displayed
 * on the right of the application.
 * This panel corresponds to the {@code find} command.
 */

/*
public class FindModulePanel extends UiPart<Region> {
    private static final String FXML = "FindModulePanel.fxml";

    public final Module module;

    @javafx.fxml.FXML
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

    public FindModulePanel(Module module) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getCode());
        moduleName.setText(module.getName());
        moduleType.setText("Fulfils: " + module.getType().toString());
        creditCount.setText("Modular Credits: " + Integer.toString(module.getCreditCount()));

        for (ModuleInfo m : module.getPreclusions()) {
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

        for (ModuleInfo m : module.getPrerequisites()) {
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
        FindModulePanel panel = (FindModulePanel) other;
        return moduleCode.equals(panel.moduleCode);
    }
}
*/
