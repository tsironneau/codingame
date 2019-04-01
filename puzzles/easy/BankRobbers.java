package easy;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://www.codingame.com/training/easy/bank-robbers
 */
public final class BankRobbers {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int robbersCount = in.nextInt();
        final int vaultCount = in.nextInt();

        final List<Double> combinationsNumber = IntStream.range(0, vaultCount)
                .mapToDouble(i -> numberOfCombinations(in.nextInt(), in.nextInt()))
                .boxed()
                .collect(Collectors.toList());

        long result = 0;
        while (!combinationsNumber.isEmpty()) {
            final double value = findEasiestVaultWithin(combinationsNumber, robbersCount);
            decreaseFromValue(value, combinationsNumber, robbersCount);
            combinationsNumber.removeIf(i -> i == 0);
            result += value;
        }

        System.out.println(result);
    }

    private static void decreaseFromValue(final double value, final List<Double> toDecrease, final int toIndex) {
        for (int i = 0, n = Math.min(toDecrease.size(), toIndex); i < n; i++) {
            toDecrease.set(i, toDecrease.get(i) - value);
        }
    }

    private static Double findEasiestVaultWithin(final List<Double> combinationsNumber, final int robbersCount) {
        return combinationsNumber
                .subList(0, Math.min(robbersCount, combinationsNumber.size()))
                .stream()
                .min(Comparator.naturalOrder())
                .orElse((double) 0);
    }

    private static double numberOfCombinations(final int charactersCount, final int digitsCount) {
        final int vowelsCount = charactersCount - digitsCount;
        return StrictMath.pow(10, digitsCount) * Math.pow(5, vowelsCount);
    }
}
