package easy;

import java.util.Arrays;
import java.util.Scanner;

/*
https://www.codingame.com/ide/puzzle/chuck-norris
 */
public final class ChuckNorris {
    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final String MESSAGE = in.nextLine();

        System.err.println("Debug messages... MESSAGE = " + MESSAGE);
        final String binarForm = toBinar(MESSAGE);

        System.err.println("Debug messages... binarForm = " + binarForm);
        final String unarForm = toUnar(binarForm);

        System.out.println(unarForm);
    }

    private static String toBinar(final String message) {
        final byte[] toBytes = message.getBytes();
        System.err.println("Debug messages... binarForm = " + Arrays.toString(toBytes));
        return toBinary(toBytes);
    }

    private static String toBinary(final byte[] bytes) {
        final StringBuilder sb = new StringBuilder(bytes.length * 7);
        for (final byte aByte : bytes) {
            for (int j = 2; j <= Byte.SIZE; j++) {
                final int currentDigit = (aByte >> (Byte.SIZE - j) & 0x01);
                final char digit = currentDigit == 0 ? '0' : '1';
                sb.append(digit);
            }
        }

        return sb.toString();
    }

    private static String toUnar(final String binarForm) {
        if (binarForm.length() == 0)
            return "";

        final StringBuilder sb = new StringBuilder();

        final char firstDigit = binarForm.charAt((0));
        boolean zero;
        if (firstDigit == '0') {
            zero = true;
            sb.append("00 0");
        } else {
            zero = false;
            sb.append("0 0");
        }

        for (int i = 1; i < binarForm.length(); i++) {
            final char digit = binarForm.charAt((i));
            if (zero) {
                if ('0' == digit) {
                    sb.append('0');
                } else {
                    zero = false;
                    sb.append(' ');
                    sb.append("0 0");
                }
            } else {
                if ('1' == digit) {
                    sb.append('0');
                } else {
                    zero = true;
                    sb.append(' ');
                    sb.append("00 0");
                }
            }
        }

        return sb.toString();
    }
}
