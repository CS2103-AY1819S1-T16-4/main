package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.commons.util.CollectionUtil.getAnyOne;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.List;
import java.util.Set;

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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Set<Module> modules = ParserUtil.parseModuleCodes(List.of(argMultimap.getValue(PREFIX_CODE).get()));
        Module module = getAnyOne(modules).get();

        return new FindCommand(module);
    }
}
