package seedu.planner.ui;

import static seedu.planner.model.util.IndexUtil.convertIndexToYearAndSemester;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.ui.ModulePanelSelectionChangedEvent;
import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * Panel containing a list of modules.
 */
public class ModuleListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleListPanel.fxml";

    private static final String YEAR = "Year ";

    private static final String DIVIDER = " | ";

    private static final String SEMESTER = "Semester ";

    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);

    private final ModulePanelType type;

    @FXML
    private Label title;

    @FXML
    private Label subTitle;

    @FXML
    private ListView<Module> moduleListView;

    public ModuleListPanel(ObservableList<Module> moduleList, int index, ModulePanelType type) {
        super(FXML);
        this.type = type;

        setConnections(moduleList);
        setHeader(index, type);
        registerAsAnEventHandler(this);
    }

    /**
     * Sets {@code this} to be timeless. Timeless refers to the
     * state of {@code this} where no modules are displayed, the
     * subtitle is not displayed, and only the title is displayed.
     *
     * @return {@code this}
     */
    public ModuleListPanel timeless() {
        subTitle.setText("");
        return this;
    }

    private void setConnections(ObservableList<Module> moduleList) {
        moduleListView.setItems(moduleList);
        moduleListView.setCellFactory(listView -> new ModuleListPanel.ModuleListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        moduleListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in person list panel changed to : '" + newValue + "'");
                        raise(new ModulePanelSelectionChangedEvent(newValue));
                    }
                });
    }

    private void setHeader(int index, ModulePanelType type) {
        String yearAndSemester = convertIndexToYearAndSemester(index);
        String[] splitYearAndSemester = yearAndSemester.split("");
        String year = splitYearAndSemester[0];
        String semester = splitYearAndSemester[1];
        title.setText("Modules " + type.toString());
        subTitle.setText(YEAR + year + DIVIDER + SEMESTER + semester);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module} using a {@code ModuleCard}.
     */
    class ModuleListViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCard(module).getRoot());
            }
        }
    }

}
