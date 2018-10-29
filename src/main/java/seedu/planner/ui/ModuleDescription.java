package seedu.planner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;

/**
 * A UI component that displays information of a {@code Module}.
 * This class is meant to be subclassed since it does not specify
 * its corresponding fxml file.
 */
public class ModuleDescription extends UiPart<Region> {

    public final Module module;

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

    public ModuleDescription(Module module, String fxml) {
        super(fxml);
        this.module = module;
        moduleCode.setText(module.getCode());
        moduleName.setText(module.getName());
        moduleType.setText("Fulfils: " + module.getType().toString());
        creditCount.setText("Modular Credits: " + Float.toString(module.getCreditCount()));

        for (ModuleInfo m : module.getPreclusions()) {
            preclusions.getChildren().add(new Label(m.getCode()));
        }

        switch(module.getPreclusions().size()) {
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

        switch(module.getPrerequisites().size()) {
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
}
