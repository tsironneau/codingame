package medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
https://www.codingame.com/training/medium/stock-exchange-losses
 */
public final class StockExchangeLosses {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();

        final List<Integer> values = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int v = in.nextInt();
            values.add(v);
        }

        int maxBuy = values.get(0);
        final int firstSell = values.get(1);
        int maxLoss = Math.min(0, firstSell - maxBuy);
        for (int i = 2; i < n; i++) {
            final int value = values.get(i);
            if (value > maxBuy) {
                maxBuy = value;
                continue;
            }
            final int diff = value - maxBuy;
            if (diff < maxLoss) {
                maxLoss = diff;
            }
        }

        System.out.println(maxLoss);
    }
}
