package easy;

import java.util.*;
import java.util.stream.IntStream;

/*
https://www.codingame.com/training/easy/the-travelling-salesman-problem
 */
public final class TravelingSalesman {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final List<double[]> towns = new ArrayList<>();
        final int N = in.nextInt();
        IntStream.range(0, N)
                .forEach(i -> towns.add(new double[]{in.nextInt(), in.nextInt()})
                );

        final double[] startTown = towns.remove(0);
        double[] currentTown = startTown;
        double totalDistance = 0;
        while (!towns.isEmpty()) {
            final double[] nextTown = findAndRemoveNextTown(currentTown, towns);
            totalDistance += distance(currentTown, nextTown);
            currentTown = nextTown;
        }

        totalDistance += distance(currentTown, startTown);
        System.out.println(Math.round(totalDistance));
    }

    private static double distance(final double[] townA, final double[] townB) {
        return Math.sqrt(Math.pow(townA[0] - townB[0], 2) + Math.pow(townA[1] - townB[1], 2));
    }

    private static double[] findAndRemoveNextTown(final double[] currentTown, final List<double[]> towns) {
        final Optional<double[]> nearestTown = towns
                .stream()
                .min(Comparator.comparingDouble(a -> distance(currentTown, a)));
        nearestTown.ifPresent(towns::remove);
        return nearestTown.orElseThrow(IllegalStateException::new);
    }
}
