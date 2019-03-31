package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
https://www.codingame.com/training/medium/conway-sequence
 */
public final class ConwaySequence {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int R = in.nextInt();
        final int L = in.nextInt();

        List<Integer> conwaySuite = Collections.singletonList(R);
        for (int i = 1; i < L; i++) {
            conwaySuite = toNextConwayLine(conwaySuite);
        }

        final String result = conwaySuite.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    private static List<Integer> toNextConwayLine(final List<Integer> line) {
        final int length = line.size();

        final List<Integer> res = new ArrayList<>();
        int current = line.get(0);
        int count = 1;
        for (int i = 1; i < length; i++) {
            final int c = line.get(i);
            if (c == current) {
                count++;
            } else {
                res.add(count);
                res.add(current);
                current = c;
                count = 1;
            }
        }
        res.add(count);
        res.add(current);
        System.err.println("Debug messages... " + res);
        return res;
    }
}
