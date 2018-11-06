package seedu.planner.logic.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import org.junit.Test;

public class CliSyntaxTest {

    @Test
    public void isPrefixLimitedToOne() {
        assertTrue(CliSyntax.isPrefixLimitedToOne(PREFIX_SEMESTER));
        assertTrue(CliSyntax.isPrefixLimitedToOne(PREFIX_YEAR));
        assertTrue(CliSyntax.isPrefixLimitedToOne(PREFIX_MAJOR));
        assertFalse(CliSyntax.isPrefixLimitedToOne(PREFIX_CODE));
        assertFalse(CliSyntax.isPrefixLimitedToOne(PREFIX_CODE));

    }
}
