package easy;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://www.codingame.com/training/easy/horse-racing-duals
 */
public final class HorseRace {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt();

        final List<Integer> powerList = IntStream.range(0, N)
                .map(i -> in.nextInt())
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < powerList.size() - 1; i++) {
            final int current = powerList.get(i);
            final int next = powerList.get(i + 1);
            final int currentDiff = next - current;
            if (minDiff > currentDiff) {
                minDiff = currentDiff;
                if (minDiff == 0)
                    break;
            }
        }

        System.out.println(minDiff);
    }
}
