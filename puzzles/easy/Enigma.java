package easy;

import java.util.Scanner;

/*
https://www.codingame.com/training/easy/encryptiondecryption-of-enigma-machine
 */
public final class Enigma {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String MAPPING;

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final String operation = in.nextLine();
        final int pseudoRandomNumber = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        final String[] rotors = new String[]{
                in.nextLine(),
                in.nextLine(),
                in.nextLine()
        };
        MAPPING = buildCharacterMapping(pseudoRandomNumber, rotors);

        final String message = in.nextLine();

        System.out.println(applyMappingTo(message, operation));
    }

    private static String buildCharacterMapping(final int pseudoRandomNumber, final String[] rotors) {
        final String caesar = ALPHABET.substring(pseudoRandomNumber) + ALPHABET.substring(0, pseudoRandomNumber);
        char[] result = caesar.toCharArray();
        for (final String rotor : rotors) {
            result = applyRotor(rotor, new String(result));
        }
        return new String(result);
    }

    private static char[] applyRotor(final CharSequence rotor1, final CharSequence caesar) {
        final char[] result = new char[ALPHABET.length()];
        for (int i = 0; i < ALPHABET.length(); i++) {
            result[i] = rotor1.charAt(ALPHABET.indexOf(caesar.charAt(i)));
        }
        return result;
    }

    private static String applyMappingTo(final String message, final String operation) {
        final char[] chars = message.toCharArray();
        final StringBuilder stringBuilder = new StringBuilder();
        final boolean encode = "ENCODE".equals(operation);
        for (int i = 0; i < chars.length; i++) {
            final char aChar = chars[i];
            if (encode) {
                final char caesarIncrement = ALPHABET.charAt((ALPHABET.indexOf(chars[i]) + i) % ALPHABET.length());
                stringBuilder.append(MAPPING.charAt(ALPHABET.indexOf(caesarIncrement)));
            } else {
                final char rawChar = ALPHABET.charAt(MAPPING.indexOf(aChar));
                final int caesarDecrementIndex = ((ALPHABET.length() * ((i + ALPHABET.length()) / ALPHABET.length()) + ALPHABET.indexOf(rawChar) - i)) % ALPHABET.length();
                stringBuilder.append(ALPHABET.charAt(caesarDecrementIndex));
            }
        }
        return stringBuilder.toString();
    }

}
