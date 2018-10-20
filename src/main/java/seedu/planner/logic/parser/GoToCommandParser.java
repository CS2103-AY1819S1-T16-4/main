package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.planner.logic.commands.GoToCommand;
import seedu.planner.logic.parser.exceptions.ParseException;

/**
 * A parser that parses an argument for the {@code GoToCommandParser}.
 */
public class GoToCommandParser implements Parser<GoToCommand> {

    @Override
    public GoToCommand parse(String arg) throws ParseException {
        try {
            int tabIndex = ParserUtil.parseTabName(arg.toUpperCase());
            return new GoToCommand(arg, tabIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE), pe);
        }
    }
}
