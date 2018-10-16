package seedu.planner.logic;

import javafx.collections.ObservableList;
import seedu.planner.logic.commands.CommandResult;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.Module;
import seedu.planner.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    //@@author GabrielYik

    //TODO: confirm if the list is filtered or sorted or both
    /** Returns an unmodifiable view of the filtered list of taken modules */
    ObservableList<Module> getFilteredTakenModuleList(int semesterIndex);

    //TODO: confirm if the list is filtered or sorted or both
    /** Returns an unmodifiable view of the filtered list of available modules */
    ObservableList<Module> getFilteredAvailableModuleList(int semesterIndex);

    //@@author

    /** Returns the list of input entered by the user, encapsulated in a {@code ListElementPointer} object */
    ListElementPointer getHistorySnapshot();
}
