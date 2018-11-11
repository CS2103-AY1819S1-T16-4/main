package seedu.planner.ui;

import static seedu.planner.commons.events.ui.ListModulesEvent.ALL_YEARS;
import static seedu.planner.model.ModulePlanner.MAX_NUMBER_SEMESTERS;
import static seedu.planner.ui.ModuleListPanel.TIMELESS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.planner.commons.core.Config;
import seedu.planner.commons.core.GuiSettings;
import seedu.planner.commons.core.LogsCenter;
import seedu.planner.commons.events.ui.AddModuleEvent;
import seedu.planner.commons.events.ui.ClearEvent;
import seedu.planner.commons.events.ui.ExitAppRequestEvent;
import seedu.planner.commons.events.ui.FindModuleEvent;
import seedu.planner.commons.events.ui.GoToEvent;
import seedu.planner.commons.events.ui.ListModulesEvent;
import seedu.planner.commons.events.ui.ShowHelpRequestEvent;
import seedu.planner.commons.events.ui.StatusEvent;
import seedu.planner.commons.events.ui.SuggestModulesEvent;
import seedu.planner.logic.Logic;
import seedu.planner.model.UserPrefs;
import seedu.planner.model.module.Module;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;

    private Logic logic;

    private UserPrefs prefs;

    private HelpWindow helpWindow;

    private List<ModuleListPanel> takenModuleListPanels;

    private ModuleListPanel timelessTakenModuleListPanel;

    private ModuleListPanel timelessSuggestedModuleListPanel;

    private FindModulePanel timelessMultiPurposePanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private VBox takenModulesPlaceholder;

    @FXML
    private VBox suggestedModulesPlaceholder;

    @FXML
    private StackPane multiPurposePanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

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
        initTakenModulesPanels();
        initSuggestedModulesPanel();
        initListedModulesPanel();

        ResultDisplay resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(prefs.getModulePlannerFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(logic);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }


    //@@author GabrielYik

    /**
     * Initialises an empty taken modules panel and all the taken modules panels
     * with taken modules if any.
     * The taken modules placeholder is then initialised with the empty taken
     * modules panel.
     */
    private void initTakenModulesPanels() {
        takenModuleListPanels = new ArrayList<>(MAX_NUMBER_SEMESTERS + 1);

        for (int semesterIndex = 0; semesterIndex < MAX_NUMBER_SEMESTERS; semesterIndex++) {
            ObservableList<Module> modules = logic.getTakenModulesForIndex(semesterIndex);
            ModuleListPanel takenModuleListPanel = new ModuleListPanel(modules,
                    semesterIndex, ModulePanelType.TAKEN);
            takenModuleListPanels.add(semesterIndex, takenModuleListPanel);
        }

        timelessTakenModuleListPanel = new ModuleListPanel(FXCollections.emptyObservableList(),
                ModulePanelType.TAKEN);
        timelessTakenModuleListPanel.setSubTitle(TIMELESS);

        setPlaceholder(takenModulesPlaceholder, timelessTakenModuleListPanel);
    }

    //@@author Hilda-Ang

    /**
     * Initialises the listed modules panel. The listed modules panel is a variation
     * of the taken modules panel, whereby the former collates taken modules from semesters
     * within a year or within all years.
     */
    private void initListedModulesPanel() {
        ObservableList<Module> modules = logic.listTakenModules();
        ModuleListPanel takenModuleListPanel = new ModuleListPanel(modules, ModulePanelType.TAKEN);
        takenModuleListPanels.add(MAX_NUMBER_SEMESTERS, takenModuleListPanel);

        assert takenModuleListPanels.size() == 9;
    }

    //@@author GabrielYik

    /**
     * Initialises an empty suggested modules panel.
     * The suggested modules placeholder is then initialised with the empty suggested
     * mdoules panel.
     */
    private void initSuggestedModulesPanel() {
        timelessSuggestedModuleListPanel = new ModuleListPanel(FXCollections.emptyObservableList(),
                ModulePanelType.SUGGESTED);
        timelessSuggestedModuleListPanel.setSubTitle(TIMELESS);
        setPlaceholder(suggestedModulesPlaceholder, timelessSuggestedModuleListPanel);
    }

    /**
     * Sets the placeholder with new information. Any existing
     * children of {@code pane} are removed and the {@code part}
     * is added to {@code pane} as a child.
     *
     * @param pane The pane
     * @param part The part
     */
    private void setPlaceholder(Pane pane, UiPart<Region> part) {
        pane.getChildren().clear();
        pane.getChildren().add(part.getRoot());
    }

    //@@author

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

    //@@author GabrielYik

    @Subscribe
    private void handleAddModuleEvent(AddModuleEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        int indexToGoTo = event.getIndex();
        ModuleListPanel panel = takenModuleListPanels.get(indexToGoTo);
        setPlaceholder(takenModulesPlaceholder, panel);
    }

    @Subscribe
    private void handleGoToEvent(GoToEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        ModuleListPanel panel = takenModuleListPanels.get(event.getIndex());
        setPlaceholder(takenModulesPlaceholder, panel);
    }

    @Subscribe
    private void handleFindModuleEvent(FindModuleEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        FindModulePanel panel = new FindModulePanel(event.getModule());
        setPlaceholder(multiPurposePanelPlaceholder, panel);
    }

    @Subscribe
    private void handleClearEvent(ClearEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        clearTakenModulesPanel();
        clearSuggestedModulesPanel();
        clearMultiPurposePanel();
    }

    private void clearTakenModulesPanel() {
        setPlaceholder(takenModulesPlaceholder, timelessTakenModuleListPanel);
    }

    private void clearSuggestedModulesPanel() {
        setPlaceholder(suggestedModulesPlaceholder, timelessSuggestedModuleListPanel);
    }

    private void clearMultiPurposePanel() {
        multiPurposePanelPlaceholder.getChildren().clear();
    }

    @Subscribe
    private void handleSuggestModulesEvent(SuggestModulesEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        ModuleListPanel panel = new ModuleListPanel(event.getModuleList(),
                event.getIndex(), ModulePanelType.SUGGESTED);
        setPlaceholder(suggestedModulesPlaceholder, panel);
    }

    @Subscribe
    private void handleListModulesEvent(ListModulesEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        ModuleListPanel panel = takenModuleListPanels.get(MAX_NUMBER_SEMESTERS);
        if (event.getYear() == ALL_YEARS) {
            panel.setSubTitle("All years");
        } else {
            panel.setSubTitle("Year " + event.getYear());
        }
        setPlaceholder(takenModulesPlaceholder, panel);
    }

    @Subscribe
    private void handleStatusEvent(StatusEvent event) {
        StatusPanel panel = new StatusPanel(event.getStatusMessage());
        setPlaceholder(multiPurposePanelPlaceholder, panel);
    }
}
