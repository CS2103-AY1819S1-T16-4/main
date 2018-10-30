package seedu.planner.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {

    /** @see #requireAllNonNull(Collection) */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }

    /**
     * Throws NullPointerException if {@code items} or any element of {@code items} is null.
     */
    public static void requireAllNonNull(Collection<?> items) {
        requireNonNull(items);
        items.forEach(Objects::requireNonNull);
    }

    /**
     * Returns true if {@code items} contain any elements that are non-null.
     */
    public static boolean isAnyNonNull(Object... items) {
        return items != null && Arrays.stream(items).anyMatch(Objects::nonNull);
    }

    /**
     * Returns any one element of the collection.
     *
     * @param items The collection
     * @param <E> The runtime type of the elements in the collection
     * @return Any one element of the collection
     */
    public static <E> Optional<E> getAnyOne(Collection<E> items) {
        requireNonNull(items);
        if (items.isEmpty()) {
            return Optional.empty();
        }
        List<E> list = new ArrayList<>(items);
        return Optional.of(list.get(0));
    }

    /**
     * Checks if both collections have the same items, irregardless of order.
     *
     * @param items1 The first collection
     * @param items2 The second collection
     * @param <E> The runtime type of the elements in the collections
     * @return True if the both collections have the same items, irregardless of order,
     *  else false
     */
    public static <E> boolean areEqualIgnoreOrder(Collection<E> items1, Collection<E> items2) {
        requireAllNonNull(items1, items1);
        return items1.stream().allMatch(x -> items2.stream().anyMatch(y -> x.equals(y)))
                && items2.stream().allMatch(x -> items1.stream().anyMatch(y -> x.equals(y)));
    }
}
