package easy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;

/*
https://www.codingame.com/training/easy/lumen
 */
public final class Lumen {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int roomSize = in.nextInt();
        final int candleLightStrength = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        final Collection<Position> candlePositions = new HashSet<>();
        for (int i = 0; i < roomSize; i++) {
            final String LINE = in.nextLine();
            if (!LINE.contains("C"))
                continue;
            final String withoutSpaces = LINE.replaceAll(" ", "");
            final char[] chars = withoutSpaces.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                final char aChar = chars[j];
                if (aChar == 'C')
                    candlePositions.add(new Position(j, i));
            }
        }

        int darkPositionsCount = 0;
        for (int i = 0; i < roomSize; i++) {
            for (int j = 0; j < roomSize; j++) {
                final int _i = i;
                final int _j = j;
                final Optional<Position> visibleCandle = candlePositions.stream()
                        .filter(p -> Math.abs(p.x - _j) < candleLightStrength && Math.abs(p.y - _i) < candleLightStrength)
                        .findAny();
                if (!visibleCandle.isPresent())
                    darkPositionsCount++;
            }
        }

        System.out.println(darkPositionsCount);
    }

    private static class Position {
        private final int x;
        private final int y;

        private Position(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
