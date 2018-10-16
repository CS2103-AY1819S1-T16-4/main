package seedu.planner.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.planner.commons.core.index.Index;
import seedu.planner.commons.util.StringUtil;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.person.Address;
import seedu.planner.model.person.Email;
import seedu.planner.model.person.Name;
import seedu.planner.model.person.Phone;
import seedu.planner.model.tag.Tag;
import seedu.planner.model.util.ModuleUtil;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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

    //TODO
    /**
     * Parses the unverified {@code code} into a valid {@code code}. Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static String parseModuleCode(String code) throws ParseException {
        if (!ModuleUtil.hasValidCode(code)) {
            throw new ParseException(ModuleInfo.MESSAGE_MODULE_CODE_CONSTRAINTS);
        }

        return code;
    }

    //@@author Hilda-Ang

    /**
     * Parses the unverified {@code year} into a valid {@code year}. Leading and trailing whitespaces will be trimmed.
     */
    public static String parseYear(String year) {
        requireNonNull(year);
        int yearIndex = Integer.parseInt(year.trim());

        // TODO: check whether the given year is valid, otherwise throw a ParseException

        return yearIndex;
    }

    /**
     * Parses the unverified {@code semester} into a valid {@code semester}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @author Hilda-Ang
     */
    public static String parseSemester(String semester) {
        requireNonNull(semester);
        int semesterIndex = Integer.parseInt(semester.trim());

        // TODO: check whether the given semester is valid, otherwise throw a ParseException

        return semesterIndex;
    }

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
