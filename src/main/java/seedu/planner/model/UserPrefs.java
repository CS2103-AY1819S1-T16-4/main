package seedu.planner.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.planner.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs {

    private GuiSettings guiSettings;
    private Path modulePlannerFilePath = Paths.get("data", "modulePlanner.json");

    public UserPrefs() {
        setGuiSettings(500, 500, 0, 0);
    }

    public GuiSettings getGuiSettings() {
        return guiSettings == null ? new GuiSettings() : guiSettings;
    }

    public void updateLastUsedGuiSetting(GuiSettings guiSettings) {
        this.guiSettings = guiSettings;
    }

    public void setGuiSettings(double width, double height, int x, int y) {
        guiSettings = new GuiSettings(width, height, x, y);
    }

    public Path getModulePlannerFilePath() {
        return modulePlannerFilePath;
    }

    public void setModulePlannerFilePath(Path modulePlannerFilePath) {
        this.modulePlannerFilePath = modulePlannerFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return Objects.equals(guiSettings, o.guiSettings)
                && modulePlannerFilePath.toAbsolutePath().equals(o.modulePlannerFilePath.toAbsolutePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, modulePlannerFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings.toString());
        sb.append("\nLocal data file location : " + modulePlannerFilePath);
        return sb.toString();
    }

}
