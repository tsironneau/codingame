package medium;

import java.util.*;

/*
https://www.codingame.com/training/medium/mayan-calculation
 */
public final class MayaCalculation {

    private static final int BASE = 20;
    private final MayaChar[] m_mayaNumerals = new MayaChar[BASE];
    private final Map<String, Integer> m_mayaToIntValue = new HashMap<>();
    private int m_mayaCharLength;
    private int m_mayaCharHeight;
    private final MayaOperation m_operation = new MayaOperation();

    public static void main(final String[] args) {
        final MayaCalculation solution = new MayaCalculation();
        solution.readInput();

        final MayaNumber result = solution.m_operation.compute();

        result.print();
    }

    private void readInput() {
        final Scanner in = new Scanner(System.in);
        readHeightAndLength(in);
        initMayaArray();
        readMayaNumerals(in);
        readMayaOperation(in);
    }

    private void readHeightAndLength(final Scanner in) {
        m_mayaCharLength = in.nextInt();
        m_mayaCharHeight = in.nextInt();
    }

    private void initMayaArray() {
        for (int i = 0; i < m_mayaNumerals.length; i++) {
            m_mayaNumerals[i] = new MayaChar(m_mayaCharHeight);
        }
    }

    private void readMayaNumerals(final Scanner in) {
        for (int i = 0; i < m_mayaCharHeight; i++) {
            final String numeral = in.next();
            for (int j = 0; j < BASE; j++) {
                m_mayaNumerals[j].m_char[i] = numeral.substring(j * m_mayaCharLength, j * m_mayaCharLength + m_mayaCharLength);
            }
        }
        for (int i = 0; i < m_mayaNumerals.length; i++) {
            final MayaChar mayaChar = m_mayaNumerals[i];
            m_mayaToIntValue.put(mayaChar.toString(), i);
        }
    }

    private void readMayaOperation(final Scanner in) {
        final int S1 = in.nextInt();
        m_operation.m_rightOperand = new MayaNumber(S1 / m_mayaCharHeight);
        readOperand(in, S1, m_operation.m_rightOperand);

        final int S2 = in.nextInt();
        m_operation.m_leftOperand = new MayaNumber(S2 / m_mayaCharHeight);
        readOperand(in, S2, m_operation.m_leftOperand);

        m_operation.m_operation = in.next();
    }

    private void readOperand(final Scanner in, final int s1, final MayaNumber operand) {
        for (int i = 0; i < s1; i++) {
            final String num1Line = in.next();
            operand.m_number[i / m_mayaCharHeight].m_char[i % m_mayaCharHeight] = num1Line;
        }
    }

    private class MayaChar {

        final String[] m_char;

        private MayaChar(final int length) {
            m_char = new String[length];
        }

        @Override
        public String toString() {
            return "MayaChar{" +
                    "m_char=" + Arrays.toString(m_char) +
                    '}';
        }

        void print() {
            for (final String s : m_char) System.out.println(s);
        }
    }

    private class MayaNumber {
        final MayaChar[] m_number;

        private MayaNumber(final int length) {
            m_number = new MayaChar[length];
            for (int i = 0; i < m_number.length; i++) {
                m_number[i] = new MayaChar(m_mayaCharLength);
            }
        }

        MayaNumber(final List<MayaChar> numerals) {
            m_number = new MayaChar[numerals.size()];
            numerals.toArray(m_number);
        }

        long toLong() {
            long res = 0;
            for (int i = 0, n = m_number.length - 1; i <= n; i++) {
                final MayaChar mayaChar = m_number[i];
                res += m_mayaToIntValue.get(mayaChar.toString()) * Math.pow(BASE, n - i);
            }
            return res;
        }

        @Override
        public String toString() {
            return "MayaNumber{" +
                    "m_number=" + Arrays.toString(m_number) +
                    ", toLong= " + toLong() +
                    '}';
        }

        void print() {
            for (final MayaChar mayaChar : m_number) mayaChar.print();
        }
    }

    private class MayaOperation {
        MayaNumber m_leftOperand;
        MayaNumber m_rightOperand;
        String m_operation;

        private MayaNumber compute() {
            long current = toLong();
            final List<MayaChar> numerals = new ArrayList<>();
            do {
                final int v = Math.toIntExact(current % BASE);
                numerals.add(m_mayaNumerals[v]);
                current /= BASE;
            } while (current > 0);
            Collections.reverse(numerals);
            return new MayaNumber(numerals);
        }

        private long toLong() {
            final long l1 = m_rightOperand.toLong();
            final long l2 = m_leftOperand.toLong();
            switch (m_operation) {
                case "+": {
                    return l1 + l2;
                }
                case "-": {
                    return l1 - l2;
                }
                case "*": {
                    return l1 * l2;
                }
                case "/": {
                    return l1 / l2;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return "MayaOperation{" +
                    "m_leftOperand=" + m_leftOperand +
                    ", m_operation='" + m_operation + '\'' +
                    ", m_rightOperand=" + m_rightOperand +
                    '}';
        }
    }
}
