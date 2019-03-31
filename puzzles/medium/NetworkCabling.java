package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
https://www.codingame.com/training/medium/network-cabling
 */
public final class NetworkCabling {

    private static final List<Long> Y_LIST = new ArrayList<>();

    private static long X_MAX = Integer.MIN_VALUE;
    private static long X_MIN = Integer.MAX_VALUE;

    public static void main(final String[] args) {
        buildLists();

        final long wireLength = computeWireLength();

        System.out.println(wireLength);
    }

    private static void buildLists() {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            final long x = in.nextInt();
            final long y = in.nextInt();
            Y_LIST.add(y);
            X_MAX = Math.max(x, X_MAX);
            X_MIN = Math.min(x, X_MIN);
        }
    }

    private static long findMedian() {
        Collections.sort(Y_LIST);

        if (Y_LIST.size() == 1)
            return Y_LIST.get(0);
        if (Y_LIST.size() == 2)
            return (Y_LIST.get(0) + Y_LIST.get(1)) / 2;
        if (Y_LIST.size() % 2 == 1)
            return Y_LIST.get((int) Math.floor(Y_LIST.size() / 2));

        return (Y_LIST.get(Y_LIST.size() / 2) + Y_LIST.get(Y_LIST.size() / 2 - 1)) / 2;
    }

    private static long computeWireLength() {
        final long meanNearestY = findMedian();
        long wireLength = Math.abs(X_MAX - X_MIN);

        for (final Long y : Y_LIST)
            wireLength += Math.abs(meanNearestY - y);

        return wireLength;
    }
}
