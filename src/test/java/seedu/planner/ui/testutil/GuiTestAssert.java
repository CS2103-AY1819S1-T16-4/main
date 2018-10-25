package seedu.planner.ui.testutil;

import static org.junit.Assert.assertEquals;

import guitests.guihandles.ResultDisplayHandle;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    // TODO: Create assertion tests

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
