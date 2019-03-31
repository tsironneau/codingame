package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
https://www.codingame.com/training/medium/telephone-numbers
 */
public final class TelephoneNumbers {

    private final static Map<Character, Composite> DICTIONARY = new HashMap<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt();

        for (int i = 0; i < N; i++) {
            final String telephone = in.next();
            placeInDictionnary(telephone);
        }

        final int size = DICTIONARY.values().stream().mapToInt(Composite::size).sum();

        System.out.println(size);
    }

    private static void placeInDictionnary(final String telephone) {
        final char firstChar = telephone.charAt(0);
        Composite composite = DICTIONARY.get(firstChar);
        if (composite == null) {
            DICTIONARY.put(firstChar, buildComposite(telephone));
            return;
        }
        for (int r = 1; r < telephone.length(); r++) {
            final char aChar = telephone.charAt(r);
            if (composite.m_children.get(aChar) == null) {
                composite.m_children.put(aChar, buildComposite(telephone.substring(r)));
                return;
            }
            composite = composite.m_children.get(aChar);
        }
    }

    private static Composite buildComposite(final String s) {
        final Composite res = new Composite(s.charAt(0));
        Composite currentComposite = res;
        for (int i = 1; i < s.length(); i++) {
            final char currentChar = s.charAt(i);
            final Composite newComposite = new Composite(currentChar);
            currentComposite.m_children.put(currentChar, newComposite);
            currentComposite = newComposite;
        }
        return res;
    }

    private static class Composite {
        private final char m_value;
        private final Map<Character, Composite> m_children = new HashMap<>();

        Composite(final char value) {
            m_value = value;
        }

        int size() {
            return 1 + m_children.values().stream().mapToInt(Composite::size).sum();
        }

        @Override
        public String toString() {
            return "Composite{" +
                    "m_value=" + m_value +
                    ", m_children=" + m_children +
                    '}';
        }
    }
}
