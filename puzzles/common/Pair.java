package common;

public final class Pair<A, B> {

    private final A first;
    private final B second;

    private Pair(final A first, final B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> of(final A first, final B second) {
        return new Pair<>(first, second);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public String toString() {
        return "Pair[" + this.first + "," + this.second + "]";
    }

}
