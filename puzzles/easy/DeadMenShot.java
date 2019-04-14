package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
https://www.codingame.com/training/easy/dead-mens-shot
 */
public final class DeadMenShot {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt();
        final List<int[]> corners = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            final int x = in.nextInt();
            final int y = in.nextInt();

            corners.add(new int[]{x, y});
        }
        corners.add(corners.get(0));
        final int M = in.nextInt();
        for (int i = 0; i < M; i++) {
            final int x = in.nextInt();
            final int y = in.nextInt();

            if (isInTarget(corners, x, y))
                System.out.println("hit");
            else
                System.out.println("miss");
        }
    }

    private static boolean isInTarget(final List<int[]> corners, final int x, final int y) {
        for (int i = 0; i < corners.size() - 1; i++) {
            final int[] pointA = corners.get(i);
            final int[] pointB = corners.get(i + 1);
            if (isInTriangle(pointA, pointB, new int[]{x, y}))
                return true;
        }
        return false;
    }

    private static boolean isInTriangle(final int[] pointA, final int[] pointB, final int[] testedPoint) {


        return false;
    }

}
