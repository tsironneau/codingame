package easy;

import java.util.Scanner;
import java.util.StringJoiner;

/*
https://www.codingame.com/training/easy/may-the-triforce-be-with-you
 */
public final class MayTheTriforceBeWithYou {
    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt();

        final StringJoiner joiner = new StringJoiner("\n");

        buildForceTriforce(N, joiner);
        buildWisdomAndCourageTriforces(N, joiner);
        final String triforce = setPointAtFirst(joiner);

        System.out.println(triforce);
    }

    private static String setPointAtFirst(final StringJoiner joiner) {
        final StringBuilder builder = new StringBuilder(joiner.toString());
        builder.replace(0, 1, ".");
        return builder.toString();
    }

    private static void buildWisdomAndCourageTriforces(final int n, final StringJoiner joiner) {
        for (int i = 0; i < n; i++) {
            String line = "";
            for (int j = 0; j < (n - i - 1); j++) {
                line += ' ';
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                line += '*';
            }
            for (int j = 0; j < (2 * n - 1 - i * 2); j++) {
                line += ' ';
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                line += '*';
            }
            joiner.add(line);
        }
    }

    private static void buildForceTriforce(final int n, final StringJoiner joiner) {
        for (int i = 0; i < n; i++) {
            String line = "";
            for (int j = 0; j < n * 2 - i - 1; j++) {
                line += ' ';
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                line += '*';
            }
            joiner.add(line);
        }
    }
}
