package easy;

import java.util.Scanner;

/*
https://www.codingame.com/training/easy/brackets-extreme-edition
 */
public final class BracketsExtremeEdition {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        //Remove all non [](){} characters
        String s = in.next().replaceAll("[^\\[\\](){}]", "");
        String next = "";
        while (next.length() != s.length()) {
            next = s;
            //Remove paired () {} []
            s = s.replaceAll("(\\(\\)|\\[]|\\{})", "");
        }

        System.out.println(s.isEmpty());
    }
}
