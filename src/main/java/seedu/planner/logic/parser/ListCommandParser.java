package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.planner.model.util.IndexUtil.VALUE_NOT_AVAILABLE;

import java.util.logging.Logger;

import seedu.planner.commons.core.LogsCenter;
import seedu.planner.logic.commands.ListCommand;
import seedu.planner.logic.parser.exceptions.ParseException;

//@@author Hilda-Ang

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static Logger logger = LogsCenter.getLogger(ListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        logger.info("parsing arguments for list command " + args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_YEAR);

        if (!argMultimap.getPreamble().isEmpty()) {
            logger.warning("error in parsing arguments for list command due to invalid command format" + args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String year = argMultimap.getValue(PREFIX_YEAR).orElse(null);

        if (year == null) {
            return new ListCommand(VALUE_NOT_AVAILABLE);
        }

        return new ListCommand(ParserUtil.parseYear(year));
    }
}
