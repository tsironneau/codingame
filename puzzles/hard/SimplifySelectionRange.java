package hard;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.codingame.com/ide/puzzle/simplify-selection-ranges
 */
public final class SimplifySelectionRange {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final String N = in.nextLine();

        final String withoutBrackets = removeBrackets(N);
        final int[] ints = toSortedIntegers(withoutBrackets);
        final String res = buildResult(ints);

        System.out.println(res);
    }

    private static String removeBrackets(final String n) {
        return n.replaceAll("(\\[|\\])", "");
    }

    private static int[] toSortedIntegers(final String withoutBrackets) {
        final String[] split = withoutBrackets.split(",");
        final int[] ints = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(ints);
        return ints;
    }

    private static String buildResult(final int[] ints) {
        int followingNumbers = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ints.length - 1; i++) {
            final int current = ints[i];
            if (followingNumbers == 0)
                res.append(current).append(",");

            final int next = ints[i + 1];
            if (next == current + 1) {
                followingNumbers++;
                if (followingNumbers == 2)
                    res = new StringBuilder(res.substring(0, res.length() - 1) + "-");
            } else {
                if (followingNumbers > 0)
                    res.append(current).append(",");
                followingNumbers = 0;
            }
        }

        res.append(ints[ints.length - 1]);
        return res.toString();
    }
}
