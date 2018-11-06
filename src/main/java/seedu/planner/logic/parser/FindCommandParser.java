package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.commons.util.CollectionUtil.getAnyOne;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.List;
import java.util.Set;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.commands.FindCommand;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.Module;

/**
 * A parser that parses an argument for the {@code FindCommand}.
 */
public class FindCommandParser implements Parser<FindCommand> {

    @Override
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_CODE);

        if (!argMultimap.containsAllPrefixes(PREFIX_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
            throw new ParseException(errorMessage);
        }

        if (argMultimap.checkPrefixValueCount(PREFIX_CODE) != 1) {
            String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format(Messages.MESSAGE_EXTRA_PREFIX_VALUES, PREFIX_CODE));
            throw new ParseException(errorMessage);
        }

        Set<Module> modules = ParserUtil.parseModuleCodes(List.of(argMultimap.getValue(PREFIX_CODE).get()));
        Module module = getAnyOne(modules).get();

        return new FindCommand(module);
    }
}
