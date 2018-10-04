package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.planner.logic.commands.DeleteModuleCommand;
import seedu.planner.logic.parser.exceptions.ParseException;

//@@author GabrielYik

/**
 *
 */
public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteModuleCommand
     * and returns a DeleteModuleCommand object for execution.
     *
     * @throws ParseException If the user input does not conform to the expected format
     */
    @Override
    public DeleteModuleCommand parse(String args) throws ParseException {
        try {
            String code = ParserUtil.parseModuleCode(args);
            return new DeleteModuleCommand(code);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
