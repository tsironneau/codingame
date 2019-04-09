package easy;

import java.util.Scanner;

/*
https://www.codingame.com/training/easy/the-river-ii-
 */
public final class TheRiverII {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int r = in.nextInt();
        final int rDigits = (int) StrictMath.log10(r) + 1;
        final int lastPossiblePreviousValue = r - rDigits * 9;

        int riverPreviousValue = r;
        while (riverPreviousValue > 0 && riverPreviousValue > lastPossiblePreviousValue) {
            if (r == nextRiverValue(riverPreviousValue)) {
                System.out.println("YES");
                return;
            }
            riverPreviousValue--;
        }

        System.out.println("NO");
    }

    private static long nextRiverValue(final long aLong) {
        return aLong + sumOfDigits(aLong);
    }

    private static long sumOfDigits(final long aLong) {
        return aLong == 0 ? 0 : aLong % 10 + sumOfDigits(aLong / 10);
    }
}
