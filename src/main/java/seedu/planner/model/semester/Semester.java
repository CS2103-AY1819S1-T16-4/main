package seedu.planner.model.semester;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.planner.model.module.Module;

/**
 * Represents a Semester in the module planner.
 * Holds the list of modules taken and the list of modules available for
 * that semester.
 * <p>
 * A semester can be characterised by the year it is in, and whether it is
 * the first or second semester in that year.
 */
public class Semester {

    // Basic information
    private final int index;
    private final int year;

    // Modules lists
    private ObservableList<Module> takenModules;

    /**
     * Constructs a {@code Semester}.
     *
     * @param year  A valid year
     * @param index A valid index
     */
    public Semester(int year, int index) {
        this.year = year;
        this.index = index;
        this.takenModules = FXCollections.observableArrayList();
    }

    /**
     * Constructs a {@code Semester} with the
     * modules from {@code semester}.
     *
     * @param semester The semester which modules are used
     *                 to construct the new semester
     */
    public Semester(Semester semester) {
        year = semester.getYear();
        index = semester.getIndex();
        takenModules = semester.getModulesAsCopy();
    }

    public int getIndex() {
        return index;
    }

    public int getYear() {
        return year;
    }

    /**
     * Adds one or more module(s) to the set of modules taken.
     *
     * @param modules A non-empty set of modules to be added
     */
    public void addModules(Set<Module> modules) {
        takenModules.addAll(modules);
    }

    /**
     * Deletes one or more module(s) from set of modules taken if present.
     *
     * @param modules A non-empty set of modules to be deleted
     */
    public void deleteModules(Set<Module> modules) {
        for (Module m : modules) {
            takenModules.remove(m);
        }
    }

    /**
     * Checks if the module with is taken or planned to take in the semester.
     *
     * @param module The module
     * @return True if the module exists, false if not
     */
    public boolean containsModule(Module module) {
        requireNonNull(module);
        return takenModules.stream().anyMatch(module::equals);
    }

    /**
     * Returns the list of modules taken in this semester.
     *
     * @return A list of modules taken
     */
    public ObservableList<Module> getModules() {
        return takenModules;
    }

    /**
     * Replaces all the current modules taken in {@code this}
     * with the modules taken in {@code semester}.
     *
     * @param semester The semester which modules taken are
     *                 used as replacement
     */
    public void setTakenModules(Semester semester) {
        takenModules.clear();
        takenModules.addAll(semester.getModules());
    }

    /**
     * Copies the modules of {@code this} and
     * returns them in an {@code ObservableList}.
     *
     * @return The copied modules of {@code this} wrapped
     * in an {@code ObservableList}
     */
    public ObservableList<Module> getModulesAsCopy() {
        List<Module> modulesTakenCopy = new ArrayList<>();
        for (Module module : takenModules) {
            Module moduleCopy = new Module(module.getCode());
            modulesTakenCopy.add(moduleCopy);
        }
        return FXCollections.observableList(modulesTakenCopy);
    }

    /**
     * {@code Semester} produced by JSON deserialization seems to rearrange {@code Module}s
     * in arbitrary order regardless of the original order in JSON file.
     * Use this function to check equality for this situation.
     */
    private boolean slowUnorderEquals(ObservableList<Module> otherTakenModules) {
        HashSet<Module> lhs = new HashSet<>(takenModules);
        HashSet<Module> rhs = new HashSet<>(otherTakenModules);
        return lhs.equals(rhs);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Semester // instanceof handles nulls
                && index == ((Semester) other).getIndex()
                && year == ((Semester) other).getYear()
                && slowUnorderEquals(((Semester) other).takenModules));
    }

    @Override
    public int hashCode() {
        return takenModules.hashCode();
    }

    @Override
    public String toString() {
        return "Year " + year + " | Semester " + index;
    }
}
