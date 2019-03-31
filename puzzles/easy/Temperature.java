package easy;

import java.util.*;
import java.util.stream.Collectors;

/*
https://www.codingame.com/training/easy/temperatures
 */
public final class Temperature {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt(); // the number of temperatures to analyse
        in.nextLine();
        final String temps = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526

        if (temps.isEmpty()) {
            System.out.println(0);
            return;
        }

        final String[] tempsArray = temps.split(" ");
        Arrays.stream(tempsArray)
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Math::abs))
                .entrySet()
                .stream()
                .min(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .ifPresent(l -> System.out.println(Collections.max(l)));
    }
}
