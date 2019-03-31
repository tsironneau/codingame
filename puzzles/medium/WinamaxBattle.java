package medium;

import java.util.*;

/*
https://www.codingame.com/training/medium/winamax-battle
 */
public final class WinamaxBattle {

    private static final List<Integer> deck1 = new ArrayList<>();
    private static final List<Integer> deck2 = new ArrayList<>();

    private static final List<Integer> playedCards1 = new ArrayList<>();
    private static final List<Integer> playedCards2 = new ArrayList<>();

    private static boolean bataille = false;

    private static final Set<Integer> cache1 = new HashSet<>();
    private static final Set<Integer> cache2 = new HashSet<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);

        buildDecks(in);

        int count = 0;
        while (!gameOver()) {
            System.err.println("Debug messages... " + deck1 + ", " + deck2);
            System.err.println("Debug messages... " + count);
            count++;
            //Infinite game am i wrong somewhere ???
            if (cache1.contains(deck1.hashCode()) && cache2.contains(deck2.hashCode())) {
                System.out.println("PAT");
                return;
            }
            cache1.add(deck1.hashCode());
            cache2.add(deck2.hashCode());
            playNextDuel();
        }

        if (bataille) {
            System.out.println("PAT");
        } else {
            System.out.println(getWinner() + " " + count);
        }
    }

    private static int getWinner() {
        if (deck1.isEmpty())
            return 2;
        return 1;
    }

    private static void playNextDuel() {
        if (gameOver())
            return;

        playedCards1.add(deck1.remove(0));
        playedCards2.add(deck2.remove(0));

        final int winner = resolveCurrentDuel();
        if (winner != 0)
            enqueuePlayedCards(winner);
        else
            bataille();
    }

    private static void bataille() {
        bataille = true;
        if (deck1.size() < 4 || deck2.size() < 4)
            return;

        for (int i = 0; i < 3; i++) {
            playedCards1.add(deck1.remove(0));
            playedCards2.add(deck2.remove(0));
        }

        playNextDuel();

        bataille = false;
    }

    private static void enqueuePlayedCards(final int winner) {
        if (winner == 1) {
            deck1.addAll(playedCards1);
            deck1.addAll(playedCards2);
        } else {
            deck2.addAll(playedCards1);
            deck2.addAll(playedCards2);
        }
        playedCards1.clear();
        playedCards2.clear();
    }

    private static int resolveCurrentDuel() {
        final int compare = Integer.compare(playedCards1.get(playedCards1.size() - 1), playedCards2.get(playedCards2.size() - 1));
        return (compare == 0) ? 0 : (compare > 0) ? 1 : 2;
    }

    private static boolean gameOver() {
        return deck1.isEmpty() || deck2.isEmpty();
    }

    private static void buildDecks(final Scanner in) {
        final int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            final String cardp1 = in.next(); // the n cards of player 1
            deck1.add(getCardValue(cardp1));
        }
        final int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            final String cardp2 = in.next(); // the m cards of player 2
            deck2.add(getCardValue(cardp2));
        }
    }

    private static int getCardValue(final String card) {
        int res = 0;
        final String value = card.substring(0, 1);
        try {
            res = Integer.parseInt(value);
            if (res == 1)
                res = 10;
        } catch (final Exception e) {
            switch (value) {
                case "J":
                    res = 11;
                    break;
                case "Q":
                    res = 12;
                    break;
                case "K":
                    res = 13;
                    break;
                case "A":
                    res = 14;
                    break;
            }
        }
        return res;
    }
}
