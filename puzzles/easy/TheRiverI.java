package easy;

import java.util.Scanner;

/*
https://www.codingame.com/training/easy/the-river-i-
 */
public final class TheRiverI {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        long r1 = in.nextLong();
        long r2 = in.nextLong();

        while (r1 != r2) {
            if (r1 > r2)
                r2 = nextRiverValue(r2);
            else
                r1 = nextRiverValue(r1);
        }

        System.out.println(r1);
    }

    private static long nextRiverValue(final long aLong) {
        return aLong + sumOfDigits(aLong);
    }

    private static long sumOfDigits(final long aLong) {
        return aLong == 0 ? 0 : aLong % 10 + sumOfDigits(aLong / 10);

    }

}
