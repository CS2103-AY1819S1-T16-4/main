package seedu.planner.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.planner.model.module.Module;

public class SuggestModulePanel extends ModuleListPanel {

    public SuggestModulePanel() {
        super(FXCollections.emptyObservableList());
    }

    public SuggestModulePanel(ObservableList<Module> moduleList) {
        super(moduleList);
    }

}
