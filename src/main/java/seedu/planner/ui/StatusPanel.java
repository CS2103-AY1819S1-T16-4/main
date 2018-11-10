package seedu.planner.ui;

import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.planner.model.course.CreditRequired;
import seedu.planner.model.course.DegreeRequirement;

/**
 * Panel to display the user's progress status.
 */
public class StatusPanel extends UiPart<Region> {
    private static final String FXML = "StatusPanel.fxml";

    @FXML
    private Label header;

    @FXML
    private Label statusMessage;

    public StatusPanel(ObservableMap<DegreeRequirement, int[]> statusMap) {
        super(FXML);

        header.setText("Status:");
        statusMessage.textProperty().bind(new StringBinding() {
            {
                bind(statusMap);
            }
            @Override
            protected String computeValue() {
                StringBuilder sb = new StringBuilder();
                sb.append(formatNonFocusArea(statusMap));
                if (statusMap.containsKey(DegreeRequirement.FOCUS_AREA_REQUIREMENTS)) {
                    sb.append(formatFocusArea(statusMap));
                }
                return sb.toString().trim();
            }
        });
    }

    /**
     * Formats the string that shows status for non-Focus Area related modules.
     *
     * @param statusMap The status mapping
     * @return String showing the progress for non-Focus Area related modules.
     */
    private String formatNonFocusArea(ObservableMap<DegreeRequirement, int[]> statusMap) {
        DegreeRequirement[] dr = DegreeRequirement.values();
        CreditRequired[] cr = CreditRequired.values();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < cr.length - 1) {
            DegreeRequirement degreeRequirement = dr[i];
            CreditRequired creditRequired = cr[i];
            int[] creditAchieved = statusMap.get(degreeRequirement);
            sb.append(degreeRequirement.toString()).append(": ").append(creditAchieved[0]);
            sb.append("/").append(creditRequired.getRequired()).append("\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * Formats the string that shows status for Focus Area modules that the user need to take.
     *
     * @param statusMap The status mapping
     * @return String showing the progress for Focus Area modules that the user need to take.
     */
    private String formatFocusArea(ObservableMap<DegreeRequirement, int[]> statusMap) {
        int j = 0;
        DegreeRequirement focusAreaRequirements = DegreeRequirement.FOCUS_AREA_REQUIREMENTS;
        CreditRequired focusAreaRequiredCredits = CreditRequired.FOCUS_AREA_REQUIREMENT;
        int[] focusAreasAchieved = statusMap.get(focusAreaRequirements);
        StringBuilder sb = new StringBuilder();
        while (j < focusAreasAchieved.length) {
            sb.append(focusAreaRequirements.toString()).append(" ").append(j + 1);
            sb.append(": ").append(focusAreasAchieved[j]).append("/");
            sb.append(focusAreaRequiredCredits.getRequired()).append("\n");
            j++;
        }
        return sb.toString();
    }
}
