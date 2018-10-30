package seedu.planner.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.AppUtil.checkArgument;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    private static final String ALL_LETTERS_REGEX = "[a-zA-Z]+( +[a-zA-Z]+)*";

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);

        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     *
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    //@@author GabrielYik

    /**
     * Checks if the String contains only alphabets.
     *
     * @param s The String
     * @return True if the String contains only alphabets
     */
    public static boolean containsOnlyLettersAndWhiteSpace(String s) {
        requireNonNull(s);
        return s.matches(ALL_LETTERS_REGEX);
    }

    /**
     * Takes the elements in a collection and combines them into a string
     * with each element separated by a whitespace.
     * The class of the element has to override the {@code toString()} method
     * for this method to work properly.
     *
     * @param collection The collection
     * @param <E> The runtime type of the collection
     * @return The string containing all the elements in the collection
     */
    public static <E> String convertCollectionToString(Collection<E> collection) {
        requireNonNull(collection);

        List<String> list = new ArrayList<>();
        for (E e : collection) {
            list.add(e.toString());
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s + " ");
        }
        return sb.toString().trim();
    }

    /**
     * Checks if two strings are equal while ignoring their cases.
     *
     * @param s1 The first string
     * @param s2 The second string
     * @return True if both strings are equal while ignoring their case,
     *  else false
     */
    public static boolean areEqualIgnoreCase(String s1, String s2) {
        requireAllNonNull(s1, s2);
        return s1.toLowerCase().equals(s2.toLowerCase());
    }

    /**
     * Capitalizes the first letter of the {@code word}.
     * If the word is made up of a single letter,
     * the letter itself is capitalized.
     *
     * @param word The word
     * @return The word with each first letter capitalized
     */
    private static String capitalizeWord(String word) {
        requireNonNull(word);

        if (word.length() == 1) {
            return word.toUpperCase();
        } else {
            return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
        }
    }

    /**
     * Capitalizes the first letter of each word in the {@code sentence}.
     *
     * @param sentence The sentence
     * @return The sentence with each word having its first
     *  letter capitalized
     */
    public static String capitalizeSentence(String sentence) {
        requireNonNull(sentence);
        String[] words = sentence.split(" ");

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (containsOnlyLettersAndWhiteSpace(word)) {
                word = capitalizeWord(word);
                sb.append(word + " ");
            }
        }

        return sb.toString().trim();
    }
}
