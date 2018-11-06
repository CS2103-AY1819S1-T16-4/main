package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.planner.model.util.IndexUtil.VALUE_NOT_AVAILABLE;

import seedu.planner.logic.commands.ListCommand;
import seedu.planner.logic.parser.exceptions.ParseException;

//@@author Hilda-Ang

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_YEAR);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String year = argMultimap.getValue(PREFIX_YEAR).orElse(null);

        if (year == null) {
            return new ListCommand(VALUE_NOT_AVAILABLE);
        }

        int intYear = ParserUtil.parseYear(year);

        return new ListCommand(intYear);
    }
}
