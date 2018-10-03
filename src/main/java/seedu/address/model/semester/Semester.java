package seedu.address.model.semester;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.Module;

/**
 * Represents a Semester in the module planner.
 */
public class Semester {

	// Basic information
	private final int semester;
	private final int year;

	// Indicator of whether user has passed the semester
	private boolean past;

	// Modules lists
	private List<Module> modulesTaken = new ArrayList<Module>();
	private List<Module> modulesSuggested = new ArrayList<Module>();

	/**
     * Constructs a {@code Semester}.
     *
     * @param semester A valid semester.
     * @param year A valid year.
     * @param past An indicator of whether the semester has been passed.
     */
    public Semester(int semester, int year, boolean past) {
        this.semester = semester;
        this.year = year;
        this.past = past;
    } 
}