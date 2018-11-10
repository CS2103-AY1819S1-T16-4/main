package seedu.planner.commons.util;

/**
 * A convenience class to represent a pair of values.
 */
public class Pair {

    private final Object first;
    private final Object second;

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pair)) {
            return false;
        }

        Pair pair = (Pair) other;
        return first.equals(pair.first) && second.equals(pair.second);
    }
}
