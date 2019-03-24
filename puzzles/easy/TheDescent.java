package easy;

import common.Pair;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * https://www.codingame.com/ide/puzzle/the-descent
 */
public final class TheDescent {
    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);

        while (true) {
            final Optional<Pair<Integer, Integer>> max =
                    IntStream.range(0, 8)
                            .mapToObj(i -> Pair.of(i, in.nextInt()))
                            .max(Comparator.comparingInt(Pair::getSecond));
            max.ifPresent(m -> System.out.println(m.getFirst()));
        }
    }
}
