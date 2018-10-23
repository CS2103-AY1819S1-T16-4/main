package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.stream.Stream;

import seedu.planner.logic.commands.GoToCommand;
import seedu.planner.logic.parser.exceptions.ParseException;

//@@author GabrielYik

/**
 * A parser that parses an argument for the {@code GoToCommandParser}.
 */
public class GoToCommandParser implements Parser<GoToCommand> {

    @Override
    public GoToCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_YEAR, PREFIX_SEMESTER);

        if (!arePrefixesPresent(argMultimap, PREFIX_YEAR, PREFIX_SEMESTER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
        }

        int year = Integer.parseInt(argMultimap.getValue(PREFIX_YEAR).get());
        int semester = Integer.parseInt(argMultimap.getValue(PREFIX_SEMESTER).get());

        return new GoToCommand(year, semester);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
