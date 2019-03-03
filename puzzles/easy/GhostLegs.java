package easy;

import java.util.Scanner;

/*
https://www.codingame.com/ide/puzzle/ghost-legs
 */
public final class GhostLegs {
    private static final String LABEL_SEPARATOR = " {2}";

    public static void main(final String... args) {
        final Scanner in = new Scanner(System.in);
        final int W = in.nextInt();
        final int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        final String[] topLabels = in.nextLine().split(LABEL_SEPARATOR);

        final String[] diagram = new String[H - 2];
        for (int i = 0; i < H - 2; i++) {
            diagram[i] = in.nextLine();
        }

        final String[] bottomLabels = in.nextLine().split(LABEL_SEPARATOR);

        for (int i = 0; i < topLabels.length; i++) {
            final String topLabel = topLabels[i];
            final int lastIndex = findLastIndex(i * 3, diagram);
            final String bottomLabel = bottomLabels[lastIndex / 3];
            System.out.println(topLabel + bottomLabel);
        }
    }

    private static int findLastIndex(final int startIndex, final String[] diagram) {
        int currentIndex = startIndex;
        for (final String s : diagram) {
            currentIndex = findNextIndex(currentIndex, s);
        }
        return currentIndex;
    }

    private static int findNextIndex(final int currentIndex, final String s) {
        if (currentIndex > 0) {
            final char c = s.charAt(currentIndex - 1);
            if (c == '-')
                return currentIndex - 3;
        }
        if (currentIndex < s.length() - 1) {
            final char c = s.charAt(currentIndex + 1);
            if (c == '-')
                return currentIndex + 3;
        }
        return currentIndex;
    }
}
