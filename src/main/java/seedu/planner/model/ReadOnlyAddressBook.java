package seedu.planner.model;

import javafx.collections.ObservableList;
import seedu.planner.model.person.Person;

/**
 * Unmodifiable view of an planner book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
