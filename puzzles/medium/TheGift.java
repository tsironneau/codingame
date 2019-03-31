package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

/*
https://www.codingame.com/training/medium/the-gift
 */
public final class TheGift {

    private static final List<Integer> BUDGET = new ArrayList<>();
    private static final List<Integer> PARTICIPATIONS = new ArrayList<>();
    private static int PRIZE;

    public static void main(final String[] args) {
        buildBudgetAndPrize();
        debugPrint();
        if (notEnoughMoney()) {
            out.println("IMPOSSIBLE");
            return;
        }
        buildParticipations();
        printParticipations();
    }

    private static void buildParticipations() {
        int remainingToAdd = PRIZE;
        for (int i = 0, n = BUDGET.size(); i < n; i++) {
            final int toEquallyDistribute = remainingToAdd / (n - i);
            final int maxToGive = Math.min(toEquallyDistribute, BUDGET.get(i));
            remainingToAdd -= maxToGive;
            PARTICIPATIONS.add(maxToGive);
        }
        Collections.sort(PARTICIPATIONS);
    }

    private static void printParticipations() {
        PARTICIPATIONS.forEach(out::println);
    }

    private static boolean notEnoughMoney() {
        final int sum = BUDGET.stream().mapToInt(i -> i).sum();
        return sum < PRIZE;
    }

    private static void buildBudgetAndPrize() {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        PRIZE = in.nextInt();
        for (int i = 0; i < n; i++) {
            final int B = in.nextInt();
            BUDGET.add(B);
        }
        Collections.sort(BUDGET);
    }

    private static void debugPrint() {
        err.println("Size " + BUDGET.size());
        err.println("Amount = " + PRIZE);
        err.println("BUDGET " + BUDGET);
    }
}
