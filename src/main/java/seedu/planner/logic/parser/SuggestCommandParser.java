package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.logging.Logger;

import seedu.planner.commons.core.LogsCenter;
import seedu.planner.logic.commands.SuggestCommand;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.util.IndexUtil;

//@@author Hilda-Ang

/**
 * Parses input arguments and creates a new SuggestCommand object.
 */
public class SuggestCommandParser implements Parser<SuggestCommand> {

    private static Logger logger = LogsCenter.getLogger(SuggestCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the SuggestCommand
     * and returns a SuggestCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SuggestCommand parse(String args) throws ParseException {
        logger.info("parsing arguments for suggest command " + args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_YEAR, PREFIX_SEMESTER);

        if (!argMultimap.containsAllPrefixes(PREFIX_YEAR, PREFIX_SEMESTER) || !argMultimap.getPreamble().isEmpty()) {
            logger.warning("error in parsing arguments for suggest command due to invalid command format" + args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
        }

        int year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        int semester = ParserUtil.parseSemester(argMultimap.getValue(PREFIX_SEMESTER).get());

        return new SuggestCommand(IndexUtil.convertYearAndSemesterToIndex(year, semester));
    }
}
