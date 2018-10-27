package seedu.planner.ui;

import static seedu.planner.model.ModulePlanner.MAX_NUMBER_SEMESTERS;
import static seedu.planner.model.ModulePlanner.NUMBER_MODULE_GROUPS;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.planner.commons.core.Config;
import seedu.planner.commons.core.GuiSettings;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.ui.ExitAppRequestEvent;
import seedu.planner.commons.events.ui.FindModuleEvent;
import seedu.planner.commons.events.ui.ShowHelpRequestEvent;
import seedu.planner.commons.events.ui.TabSwitchEvent;
import seedu.planner.logic.Logic;
import seedu.planner.model.UserPrefs;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private UserPrefs prefs;
    private HelpWindow helpWindow;

    @FXML
    private TabPane semestersTabPane;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Config config, UserPrefs prefs, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.prefs = prefs;

        // Configure the UI
        setTitle(config.getAppTitle());
        setWindowDefaultSize(prefs);

        setAccelerators();
        registerAsAnEventHandler(this);

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

        //@@author GabrielYik
        ModuleListPanel[] takenModulesListPanels = new ModuleListPanel[MAX_NUMBER_SEMESTERS];
        ModuleListPanel[] availableModulesListPanels = new ModuleListPanel[MAX_NUMBER_SEMESTERS];

        for (int semesterIndex = 0; semesterIndex < MAX_NUMBER_SEMESTERS; semesterIndex++) {
            takenModulesListPanels[semesterIndex] = new ModuleListPanel(
                    logic.getTakenModuleList(semesterIndex));
            availableModulesListPanels[semesterIndex] = new ModuleListPanel(
                    logic.getAvailableModuleList(semesterIndex));
        }

        ObservableList<Tab> semesterTabs = semestersTabPane.getTabs();
        for (int semesterIndex = 0; semesterIndex < semesterTabs.size(); semesterIndex++) {
            SplitPane splitPane = (SplitPane) semesterTabs.get(semesterIndex).getContent();
            ObservableList<Node> nodes = splitPane.getItems();

            for (int i = 0; i < NUMBER_MODULE_GROUPS; i++) {
                VBox vBox = (VBox) nodes.get(i);
                StackPane stackPane = (StackPane) vBox.getChildren().get(0);
                Node n = (i == 0)
                        ? takenModulesListPanels[semesterIndex].getRoot()
                        : availableModulesListPanels[semesterIndex].getRoot();
                stackPane.getChildren().add(n);
            }
        }

        //@@author

        ResultDisplay resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(prefs.getModulePlannerFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(logic);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    void hide() {
        primaryStage.hide();
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    /**
     * Sets the default size based on user preferences.
     */
    private void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    /**
     * Returns the current size and the position of the main Window.
     */
    GuiSettings getCurrentGuiSetting() {
        return new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        raise(new ExitAppRequestEvent());
    }

    @Subscribe
    private void handleShowHelpEvent(ShowHelpRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleHelp();
    }

    @Subscribe
    private void handleTabSwitch(TabSwitchEvent event) {
        semestersTabPane.getSelectionModel().select(event.getIndex());
    }

    @Subscribe
    private void handleFindModule(FindModuleEvent event) {
        ObservableList<Tab> semesterTabs = semestersTabPane.getTabs();
        for (int semesterIndex = 0; semesterIndex < semesterTabs.size(); semesterIndex++) {
            SplitPane splitPane = (SplitPane) semesterTabs.get(semesterIndex).getContent();
            ObservableList<Node> nodes = splitPane.getItems();
            StackPane stackPane = (StackPane) nodes.get(2);
            FindModulePanel findModulePanel = new FindModulePanel(event.getModule());
            stackPane.getChildren().add(findModulePanel.getRoot());
        }
    }
}
