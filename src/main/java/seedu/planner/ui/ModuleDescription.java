package seedu.planner.ui;

import java.util.List;

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

        List<ModuleInfo> preclusionList = module.getPreclusions();
        for (ModuleInfo m : preclusionList) {
            preclusions.getChildren().add(new Label(m.getCode()));
        }
        preclusion.setText("Preclusion" + getCorrectGrammar(preclusionList.size()));

        List<ModuleInfo> prerequisiteList = module.getPrerequisites();
        for (ModuleInfo m : prerequisiteList) {
            prerequisites.getChildren().add(new Label(m.getCode()));
        }
        prerequisite.setText("Prerequisite" + getCorrectGrammar(prerequisiteList.size()));
    }

    /**
     * Gives the correct grammar structure (singular or plural) for the
     * {@code preclusion} and {@code prerequisite} labels depending
     * on the number of items they will be representing.
     *
     * @param count The non-negative number of items
     * @return The correct grammar structure
     */
    private String getCorrectGrammar(int count) {
        if (count == 0) {
            return ": none";
        } else if (count == 1) {
            return ": ";
        } else if (count > 1) {
            return "s: ";
        } else {
            assert false;
            return "";
        }
    }
}
