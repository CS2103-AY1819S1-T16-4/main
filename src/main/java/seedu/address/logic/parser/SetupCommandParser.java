package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOCUS_AREA;

import java.util.stream.Stream;

import seedu.address.logic.commands.SetupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author rongjiecomputer

/**
 * Parses input arguments and creates a new AddModuleCommand object
 */
public class SetupCommandParser implements Parser<SetupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SetupCommand
     * and returns an SetupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MAJOR, PREFIX_YEAR, PREFIX_SEMESTER, PREFIX_FOCUS_AREA);

        // TODO(rongjiecomputer) Detect first-time user and force user to add major year and semester.

        if (!atLeastOnePrefixPresent(argMultimap, PREFIX_MAJOR, PREFIX_YEAR, PREFIX_SEMESTER, PREFIX_FOCUS_AREA)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetupCommand.MESSAGE_USAGE));
        }
        return new SetupCommand(argMultimap);
    }

    /**
     * Returns true if at least one of the prefix contains non-empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean atLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
