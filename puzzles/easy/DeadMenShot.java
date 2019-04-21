package easy;

import java.util.ArrayList;
import java.util.Arrays;
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
        corners.forEach(a -> System.err.println(Arrays.toString(a)));
        final int M = in.nextInt();
        for (int i = 0; i < M; i++) {
            final int x = in.nextInt();
            final int y = in.nextInt();
            System.err.println(x + ", " + y);
            if (isInTarget(corners, x, y))
                System.out.println("hit");
            else
                System.out.println("miss");
        }
    }

    private static boolean isInTarget(final List<int[]> corners, final int x, final int y) {
        final int[] testedPointT = {x, y};
        for (int i = 0; i < corners.size() - 1; i++) {
            final int[] pointA = corners.get(i);
            final int[] pointB = corners.get(i + 1);
            if (isInTriangle(pointA, pointB, testedPointT))
                return true;
        }
        return false;
    }

    private static boolean isInTriangle(final int[] pointA, final int[] pointB, final int[] testedPointT) {
        final int[] pointC = new int[2];//Origin
        final int[] vectAB = getVector(pointA, pointB);
        final int[] vectAT = getVector(pointA, testedPointT);
        final int[] vectAC = getVector(pointA, pointC);

        if (pointA[0] == pointB[0] && pointA[0] == pointC[0]) return false;
        if (pointA[1] == pointB[1] && pointA[1] == pointC[1]) return false;

        final boolean b1 = det(vectAB, vectAT) * det(vectAT, vectAC) >= 0;

        final int[] vectBA = getVector(pointB, pointA);
        final int[] vectBT = getVector(pointB, testedPointT);
        final int[] vectBC = getVector(pointB, pointC);

        final boolean b2 = det(vectBA, vectBT) * det(vectBT, vectBC) >= 0;

        final int[] vectCA = getVector(pointC, pointA);
        final int[] vectCM = getVector(pointC, testedPointT);
        final int[] vectCB = getVector(pointC, pointB);

        final boolean b3 = det(vectCA, vectCM) * det(vectCM, vectCB) >= 0;
        return b1 && b2 && b3;
    }

    private static long det(final int[] vector1, final int[] vector2) {
        return vector1[0] * vector2[1] - vector1[1] * vector2[0];
    }

    private static int[] getVector(final int[] pointA, final int[] pointB) {
        return new int[]{pointB[0] - pointA[0], pointB[1] - pointA[1]};
    }

}
