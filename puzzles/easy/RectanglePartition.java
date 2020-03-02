package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RectanglePartition {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int countX = in.nextInt();
        int countY = in.nextInt();

        List<Integer> segmentsInWidth = buildSegments(in, w, countX);
        List<Integer> segmentsInHeight = buildSegments(in, h, countY);

        int result = 0;
        for (int segH : segmentsInHeight) {
            for (int segW : segmentsInWidth) {
                if (segH == segW) result++;
            }
        }

        System.out.println(result);
    }

    private static List<Integer> buildSegments(Scanner in, int w, int countX) {
        List<Integer> segments = new ArrayList<>();
        segments.add(w);
        List<Integer> measurements = new ArrayList<>();
        measurements.add(0);
        measurements.add(w);
        for (int i = 0; i < countX; i++) {
            int x = in.nextInt();

            for (Integer measurement : measurements) {
                segments.add(Math.abs(x - measurement));
            }

            measurements.add(x);
        }
        return segments;
    }
}
