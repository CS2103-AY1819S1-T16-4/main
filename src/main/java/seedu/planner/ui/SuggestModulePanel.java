package seedu.planner.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.planner.model.module.Module;

/**
 * A ui for the suggest module panel that is displayed
 * on the center of the application.
 * This panel corresponds to the {@code suggest} command.
 */
public class SuggestModulePanel extends ModuleListPanel {

    public SuggestModulePanel() {
        super(FXCollections.emptyObservableList());
    }

    public SuggestModulePanel(ObservableList<Module> moduleList) {
        super(moduleList);
    }

}
