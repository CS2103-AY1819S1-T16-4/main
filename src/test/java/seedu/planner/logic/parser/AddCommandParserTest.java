package seedu.planner.logic.parser;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1010;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_MODULE_CS1231;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_SEMESTER_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_DESC_ONE;
import static seedu.planner.logic.commands.CommandTestUtil.VALID_YEAR_ONE;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.Test;

import seedu.planner.logic.commands.AddCommand;
import seedu.planner.model.module.Module;

//@@author RomaRomama

/**
 * Tests for AddCommandParser
 */
public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();
    private Set<Module> moduleSet1 = Set.of(VALID_MODULE_CS1010);
    private Set<Module> moduleSet2 = Set.of(VALID_MODULE_CS1010, VALID_MODULE_CS1231);

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE
                + VALID_MODULE_CODE_DESC_CS1010, new AddCommand(moduleSet1, 0));

        assertParseSuccess(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE
                + VALID_MODULE_CODE_DESC_CS1010 + VALID_MODULE_CODE_DESC_CS1231, new AddCommand(moduleSet2, 0));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        //missing year prefix
        assertParseFailure(parser, VALID_YEAR_ONE + VALID_SEMESTER_DESC_ONE + VALID_MODULE_CODE_DESC_CS1010,
                expectedMessage);

        //missing semester prefix
        assertParseFailure(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_ONE + VALID_MODULE_CODE_DESC_CS1010,
                expectedMessage);

        //missing code prefix
        assertParseFailure(parser, VALID_YEAR_DESC_ONE + VALID_SEMESTER_DESC_ONE + VALID_MODULE_CS1010,
                expectedMessage);
    }
}
