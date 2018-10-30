package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.planner.commons.util.StringUtil;
import seedu.planner.logic.commands.SetUpCommand;
import seedu.planner.logic.parser.exceptions.ParseException;

/**
 * A parser that parses input arguments and creates a SetUpCommand object.
 */
public class SetUpCommandParser implements Parser<SetUpCommand> {

    @Override
    public SetUpCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_YEAR, PREFIX_SEMESTER, PREFIX_MAJOR, PREFIX_FOCUS_AREA);

        if (!arePrefixesPresent(argMultimap, PREFIX_YEAR, PREFIX_SEMESTER, PREFIX_MAJOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetUpCommand.MESSAGE_USAGE));
        }

        int year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        int semester = ParserUtil.parseSemester(argMultimap.getValue(PREFIX_SEMESTER).get());
        String major = ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get());
        Set<String> focusAreas = ParserUtil.parseFocusAreas(argMultimap.getAllValues(PREFIX_FOCUS_AREA));

        String formattedMajor = StringUtil.capitalizeSentence(major);
        Set<String> formattedFocusAreas = formatFocusAreas(focusAreas);

        return new SetUpCommand(year, semester, formattedMajor, formattedFocusAreas);
    }

    /**
     * Capitalizes the first letter of each word in each focus area in {@code focusAreas}.
     *
     * @param focusAreas The focus areas
     * @return Formatted focus areas
     */
    private static Set<String> formatFocusAreas(Set<String> focusAreas) {
        Set<String> formattedFocusAreas = new HashSet<>();

        for (String focusArea : focusAreas) {
            formattedFocusAreas.add(StringUtil.capitalizeSentence(focusArea));
        }

        return formattedFocusAreas;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
