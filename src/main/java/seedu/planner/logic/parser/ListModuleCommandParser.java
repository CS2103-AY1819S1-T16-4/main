package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.stream.Stream;

import seedu.planner.logic.commands.ListModuleCommand;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.util.IndexUtil;

//@@author Hilda-Ang

/**
 * Parses input arguments and creates a new ListModuleCommand object.
 */
public class ListModuleCommandParser implements Parser<ListModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListModuleCommand
     * and returns a ListModuleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_YEAR, PREFIX_SEMESTER);

        if (!arePrefixesPresent(argMultimap, PREFIX_YEAR, PREFIX_SEMESTER) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListModuleCommand.MESSAGE_USAGE));
        }

        int year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        int semester = ParserUtil.parseSemester(argMultimap.getValue(PREFIX_SEMESTER).get());

        return new ListModuleCommand(IndexUtil.convertYearAndSemesterToIndex(year, semester));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
