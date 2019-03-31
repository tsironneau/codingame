package easy;

import java.util.Scanner;

/*
 https://www.codingame.com/training/easy/ascii-art
 */
public final class AsciiArt {
    private static final int a_ASCII_CODE = 97;
    private static final int UNKNOWN_INDEX = 26;
    private static final int CHARACTER_COUNT = 27;
    private static String[] ROWS;
    private static int L;
    private static int H;

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        L = in.nextInt();
        H = in.nextInt();
        in.nextLine();
        final String T = in.nextLine();
        ROWS = new String[H];
        for (int i = 0; i < H; i++) {
            ROWS[i] = in.nextLine();
        }

        final String answer = computeAnswer(T);
        System.out.println(answer);
    }

    private static String computeAnswer(final String input) {
        final StringBuilder output = new StringBuilder();
        final String lowerCase = input.toLowerCase();

        for (int i = 0; i < H; i++) {
            output.append(computeLine(i, lowerCase)).append('\n');
        }

        return output.toString();
    }

    private static String computeLine(final int line, final String input) {
        final StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            final int asciiCode = input.codePointAt(i);
            final int characterIndex = computeCharacterIndex(asciiCode);
            output.append(computeLineForCharacter(line, characterIndex));
        }
        return output.toString();
    }

    private static String computeLineForCharacter(final int line, final int index) {
        final int characterFirstIndex = L * index;
        return ROWS[line].substring(characterFirstIndex, characterFirstIndex + L);
    }

    private static int computeCharacterIndex(final int asciiCode) {
        final int index = asciiCode - a_ASCII_CODE;
        if (index < 0 || index > 26)
            return UNKNOWN_INDEX;
        return index;
    }
}
