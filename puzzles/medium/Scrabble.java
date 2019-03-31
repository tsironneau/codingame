package medium;

import java.util.*;

/*
https://www.codingame.com/training/medium/scrabble
 */
public final class Scrabble {

    private static final List<Character> LETTERS_SET = new ArrayList<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt();
        in.nextLine();
        for (int i = 0; i < N; i++) {
            final String W = in.nextLine();
            Dictionary.addWord(W);
            System.err.println(W);
        }

        final String letters = in.nextLine();
        final char[] chars = letters.toCharArray();
        for (final char c : chars) {
            LETTERS_SET.add(c);
        }
        System.err.println("letters = " + letters);

        final List<String> bestWords = new ArrayList<>();
        int bestScore = 0;
        for (final String word : Dictionary.WORDS_IN_ORDER) {
            final Integer score = Dictionary.WORDS_TO_POINT.get(word);
            if (bestScore > score)
                continue;
            if (!eligible(word))
                continue;
            if (bestScore == score) {
                bestWords.add(word);
            } else {
                bestScore = score;
                bestWords.clear();
                bestWords.add(word);
            }
        }

        final String bestWord = bestWords.get(0);

        System.out.println(bestWord);
    }

    private static boolean eligible(final String key) {
        final List<Character> temp = new ArrayList<>(LETTERS_SET);
        for (int i = 0; i < key.length(); i++) {
            if (temp.isEmpty())
                return false;
            final char c = key.charAt(i);
            if (!temp.remove((Character) c))
                return false;
        }
        return true;
    }

    public static final class Dictionary {

        static final List<String> WORDS_IN_ORDER = new ArrayList<>();
        static final HashMap<String, Integer> WORDS_TO_POINT = new HashMap<>();

        static void addWord(final String word) {
            WORDS_IN_ORDER.add(word);
            WORDS_TO_POINT.put(word, computePoints(word));
        }

        private static Integer computePoints(final String word) {
            final char[] chars = word.toCharArray();
            int res = 0;
            for (final char c : chars) {
                res += CharacterValue.CHAR_TO_POINT.get(c);
            }
            return res;
        }

    }

    public static final class CharacterValue {

        static final char[] ONE_POINT = {'e', 'a', 'i', 'o', 'n', 'r', 't', 'l', 's', 'u'};
        static final char[] TWO_POINTS = {'d', 'g'};
        static final char[] THREE_POINTS = {'b', 'c', 'm', 'p'};
        static final char[] FOUR_POINTS = {'f', 'h', 'v', 'w', 'y'};
        static final char[] FIVE_POINTS = {'k'};
        static final char[] EIGHT_POINTS = {'j', 'x'};
        static final char[] TEN_POINTS = {'q', 'z'};

        static final Map<Character, Integer> CHAR_TO_POINT = new HashMap<>();

        static {
            initCharToPoint(ONE_POINT, 1);
            initCharToPoint(TWO_POINTS, 2);
            initCharToPoint(THREE_POINTS, 3);
            initCharToPoint(FOUR_POINTS, 4);
            initCharToPoint(FIVE_POINTS, 5);
            initCharToPoint(EIGHT_POINTS, 8);
            initCharToPoint(TEN_POINTS, 10);
        }

        private static void initCharToPoint(final char[] set, final int value) {
            for (final char c : set) {
                CHAR_TO_POINT.put(c, value);
            }
        }
    }
}
