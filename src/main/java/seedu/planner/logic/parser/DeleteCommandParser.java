package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.Set;
import java.util.logging.Logger;

import seedu.planner.commons.core.LogsCenter;
import seedu.planner.logic.commands.DeleteCommand;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.Module;

//@@author GabrielYik

/**
 * A parser that parse an input argument and creates a DeleteCommand.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final Logger logger = LogsCenter.getLogger(DeleteCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException If the user input does not conform to the expected format
     */
    @Override
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_CODE);

        if (!argMultimap.containsAllPrefixes(PREFIX_CODE) || !argMultimap.getPreamble().isEmpty()) {
            logger.fine("In delete command parser: no module code supplied");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        Set<Module> modules = ParserUtil.parseModuleCodes(argMultimap.getAllValues(PREFIX_CODE));

        return new DeleteCommand(modules);
    }


}
