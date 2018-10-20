package seedu.planner.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.planner.model.module.ModuleInfo.MESSAGE_MODULE_CODE_CONSTRAINTS;
import static seedu.planner.model.tab.Tab.MESSAGE_TAB_NAME_CONSTRAINTS;
import static seedu.planner.model.tab.Tab.TAB_NAME_REGEX;
import static seedu.planner.model.util.IndexUtil.convertYearAndSemesterToIndex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.planner.commons.core.index.Index;
import seedu.planner.commons.util.StringUtil;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.Module;
import seedu.planner.model.person.Address;
import seedu.planner.model.person.Email;
import seedu.planner.model.person.Name;
import seedu.planner.model.person.Phone;
import seedu.planner.model.tag.Tag;
import seedu.planner.model.util.IndexUtil;
import seedu.planner.model.util.ModuleUtil;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final String MESSAGE_INVALID_YEAR = "Year is not between 1 to 4.";
    private static final String MESSAGE_INVALID_SEMESTER = "Semester is not between 1 to 2.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    //@@author GabrielYik

    /**
     * Parses the unverified {@code codes} into a valid List of {@code codes}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code codes} are invalid.
     */
    public static List<Module> parseModuleCodes(String codes) throws ParseException {
        codes = codes.trim();

        if (codes.isEmpty()) {
            throw new ParseException(MESSAGE_MODULE_CODE_CONSTRAINTS);
        }

        String[] splitCodes = codes.split(" ");

        List<Module> validModuleCodes = new ArrayList<>();
        for (String code : splitCodes) {
            if (!ModuleUtil.hasValidCodeFormat(code)) {
                throw new ParseException(MESSAGE_MODULE_CODE_CONSTRAINTS);
            }

            validModuleCodes.add(new Module(code));
        }

        return validModuleCodes;
    }

    /**
     * Parses the tab name into its respective index.
     *
     * @param tabName The tab name
     * @return The index if the tab name is valid
     * @throws ParseException if the tab name is invalid
     */
    public static int parseTabName(String tabName) throws ParseException {
        tabName = tabName.trim();
        if (!tabName.matches(TAB_NAME_REGEX)) {
            throw new ParseException(MESSAGE_TAB_NAME_CONSTRAINTS);
        }

        String[] characters = tabName.split("");
        int year = Integer.parseInt(characters[1]);
        int semester = Integer.parseInt(characters[3]);

        return convertYearAndSemesterToIndex(year, semester);
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
        if (!IndexUtil.hasValidYear(yearIndex)) {
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
        if (!IndexUtil.hasValidYear(semesterIndex)) {
            throw new ParseException(MESSAGE_INVALID_SEMESTER);
        }
        return semesterIndex;
    }

    //@@author

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String planner} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code planner} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_TAG_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
