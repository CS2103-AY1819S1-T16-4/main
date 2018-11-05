package seedu.planner.logic.parser;

import static seedu.planner.logic.parser.ParserUtil.MESSAGE_EXTRA_PREFIX_VALUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import seedu.planner.logic.parser.exceptions.ParseException;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) throws ParseException {
        List<String> values = getAllValues(prefix);
        if (values.size() > 1 && CliSyntax.isPrefixLimitedToOne(prefix)) {
            throw new ParseException(String.format(
                    MESSAGE_EXTRA_PREFIX_VALUE, prefix));
        }
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() throws ParseException {
        return getValue(new Prefix("")).orElse("");
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values.
     */
    public boolean containsAllPrefixes(Prefix... prefixes) throws ParseException {
        for (Prefix prefix : prefixes) {
            if (!getValue(prefix).isPresent()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks the number of values that are associated with the {@code prefix}.
     *
     * @param prefix The prefix which values are to be counted
     * @return The number of values
     */
    public int checkPrefixValueCount(Prefix prefix) {
        List<String> values = argMultimap.get(prefix);
        if (values == null) {
            return 0;
        }
        return values.size();
    }
}
