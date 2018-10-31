package seedu.planner.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.planner.logic.commands.SetUpCommand.MESSAGE_FOCUS_AREA_CONSTRAINTS;
import static seedu.planner.logic.commands.SetUpCommand.MESSAGE_MAJOR_CONSTRAINTS;
import static seedu.planner.model.module.ModuleInfo.MESSAGE_MODULE_CODE_CONSTRAINTS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.planner.commons.util.StringUtil;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.Module;
import seedu.planner.model.util.IndexUtil;
import seedu.planner.model.util.ModuleUtil;

/**
 * Contains utility methods used for parsing strings in the various Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_YEAR = "Year is not between 1 to 4.";
    public static final String MESSAGE_INVALID_SEMESTER = "Semester is not between 1 to 2.";


    //@@author GabrielYik

    /**
     * Parses the unverified {@code moduleCode} into a module.
     *
     * @param moduleCode The moduleCode
     * @return The module
     * @throws ParseException if the moduleCode does not meet the constraints
     */
    private static Module parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (trimmedModuleCode.isEmpty()) {
            throw new ParseException(MESSAGE_MODULE_CODE_CONSTRAINTS);
        }
        if (!ModuleUtil.hasValidCodeFormat(trimmedModuleCode)) {
            throw new ParseException(MESSAGE_MODULE_CODE_CONSTRAINTS);
        }
        return new Module(trimmedModuleCode);
    }

    /**
     * Parses the unverified {@code moduleCodes} into a valid List of {@code modules}.
     * Individual module codes are parsed using the method {@link #parseModuleCode(String) parseModuleCode}.
     *
     * @throws ParseException if the given {@code moduleCodes} do not meet the constraints.
     */
    public static Set<Module> parseModuleCodes(Collection<String> moduleCodes) throws ParseException {
        requireNonNull(moduleCodes);
        Set<Module> modules = new HashSet<>();
        for (String m : moduleCodes) {
            modules.add(parseModuleCode(m));
        }
        return modules;
    }

    /**
     * Parses a major.
     * The major is checked if it's in the correct format.
     *
     * @param major The major
     * @return The major
     * @throws ParseException if the major's format is wrong
     */
    public static String parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (!StringUtil.containsOnlyLettersAndWhiteSpace(trimmedMajor)) {
            throw new ParseException(MESSAGE_MAJOR_CONSTRAINTS);
        }
        return trimmedMajor;
    }

    /**
     * Parses a focus area.
     * The major is checked if it's in the correct format.
     *
     * @param focusArea The focus area
     * @return The focus rea
     * @throws ParseException if the focus area's format is wrong
     */
    private static String parseFocusArea(String focusArea) throws ParseException {
        requireNonNull(focusArea);
        String trimmedFocusArea = focusArea.trim();
        if (!StringUtil.containsOnlyLettersAndWhiteSpace(trimmedFocusArea)) {
            throw new ParseException(MESSAGE_FOCUS_AREA_CONSTRAINTS);
        }
        return trimmedFocusArea;
    }

    /**
     * Parses the focus areas.
     * The focus areas are checked if they're in the correct format.
     * Individual focus areas are checked using the method
     * {@link #parseFocusArea(String focusArea) parseFocusArea}.
     *
     * @param focusAreas The focus areas
     * @return The {@code Set} of focus areas
     * @throws ParseException if one of the focus areas' format is wrong
     */
    public static Set<String> parseFocusAreas(Collection<String> focusAreas) throws ParseException {
        requireNonNull(focusAreas);
        final Set<String> focusAreasSet = new HashSet<>();
        for (String focusArea : focusAreas) {
            focusAreasSet.add(parseFocusArea(focusArea));
        }
        return focusAreasSet;
    }

    //@@author Hilda-Ang

    /**
     * Parses the unverified year into a valid year index.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given year is invalid.
     */
    public static int parseYear(String year) throws ParseException {
        requireNonNull(year);
        int yearIndex = Integer.parseInt(year.trim());
        if (!IndexUtil.isValidYear(yearIndex)) {
            throw new ParseException(MESSAGE_INVALID_YEAR);
        }
        return yearIndex;
    }

    /**
     * Parses the unverified semester into a valid semester index.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given semester is invalid.
     */
    public static int parseSemester(String semester) throws ParseException {
        requireNonNull(semester);
        int semesterIndex = Integer.parseInt(semester.trim());
        if (!IndexUtil.isValidSemester(semesterIndex)) {
            throw new ParseException(MESSAGE_INVALID_SEMESTER);
        }
        return semesterIndex;
    }

}
