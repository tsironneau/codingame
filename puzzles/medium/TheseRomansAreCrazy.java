package medium;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
https://www.codingame.com/training/medium/these-romans-are-crazy!
 */
public final class TheseRomansAreCrazy {

    private static final List<String> UNITS = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX");
    private static final List<String> TEN = Arrays.asList("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC");
    private static final List<String> HUNDRED = Arrays.asList("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM");
    private static final List<String> THOUSANDS = Arrays.asList("M", "MM", "MMM", "MMMM");

    private static final Pattern PATTERN;

    static {
        final String regex;
        regex = "^(M{0,4})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        PATTERN = Pattern.compile(regex);
    }

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final String rom1 = in.next();
        final String rom2 = in.next();

        final int dec1 = convertToDecimal(rom1);
        final int dec2 = convertToDecimal(rom2);
        final String result = convertToRoman(dec1 + dec2);

        System.err.println(String.format("%s + %s = %s", dec1, dec2, dec1 + dec2));
        System.err.println(String.format("%s + %s = %s", rom1, rom2, result));
        System.out.println(result);
    }

    private static String convertToRoman(final int decimal) {
        String res = "";

        int current = decimal;
        final int thousand = current / 1000;

        current = current % 1000;
        final int hundred = current / 100;

        current = current % 100;
        final int ten = current / 10;

        final int unit = current % 10;

        res += thousand > 0 ? THOUSANDS.get(thousand - 1) : "";
        res += hundred > 0 ? HUNDRED.get(hundred - 1) : "";
        res += ten > 0 ? TEN.get(ten - 1) : "";
        res += unit > 0 ? UNITS.get(unit - 1) : "";

        return res;
    }

    private static int convertToDecimal(final String roman) {
        final Matcher matcher = PATTERN.matcher(roman);

        int res = 0;
        while (matcher.find()) {
            final int groupCount = matcher.groupCount();
            if (groupCount != 4)
                throw new IllegalArgumentException("We need 4 groups : THOUSANDS, HUNDREDS, TEN, UNITS : " + groupCount);

            final int thousand = THOUSANDS.indexOf(matcher.group(1)) + 1;
            final int hundred = HUNDRED.indexOf(matcher.group(2)) + 1;
            final int ten = TEN.indexOf(matcher.group(3)) + 1;
            final int units = UNITS.indexOf(matcher.group(4)) + 1;

            res += 1000 * thousand;
            res += 100 * hundred;
            res += 10 * ten;
            res += units;
        }
        return res;
    }

}
