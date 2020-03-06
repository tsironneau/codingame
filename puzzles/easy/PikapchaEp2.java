package easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class PikapchaEp2 {

    static final Map<Character, Function<int[], int[]>> moves;

    static {
        moves = new HashMap<>();
        moves.put('<', a -> new int[]{a[0], a[1] - 1});
        moves.put('>', a -> new int[]{a[0], a[1] + 1});
        moves.put('v', a -> new int[]{a[0] + 1, a[1]});
        moves.put('^', a -> new int[]{a[0] - 1, a[1]});
    }

    static final Map<String, Function<Character, Direction>> sides;

    static {
        sides = new HashMap<>();
        sides.put("L", c -> {
            switch (c) {
                case '<':
                    return Direction.of('<', 'v', '^');
                case '>':
                    return Direction.of('>', '^', 'v');
                case 'v':
                    return Direction.of('v', '>', '<');
                case '^':
                    return Direction.of('^', '<', '>');
                default:
                    throw new IllegalArgumentException("Unknown direction " + c);
            }
        });
        sides.put("R", c -> {
            switch (c) {
                case '<':
                    return Direction.of('<', '^', 'v');
                case '>':
                    return Direction.of('>', 'v', '^');
                case 'v':
                    return Direction.of('v', '<', '>');
                case '^':
                    return Direction.of('^', '>', '<');
                default:
                    throw new IllegalArgumentException("Unknown direction " + c);
            }
        });
    }

    static char currentDirection;
    private static char[][] map;
    private static String side;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        map = new char[height][];
        int[] initialCell = new int[2];
        for (int i = 0; i < height; i++) {
            final char[] chars = in.next().toCharArray();
            map[i] = chars;

            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                if (moves.containsKey(aChar)) {
                    initialCell[0] = i;
                    initialCell[1] = j;
                    currentDirection = aChar;
                }
            }
        }
        side = in.next();

        map[initialCell[0]][initialCell[1]] = '0';
        final int nonEmptyNeighbours = countNonEmptyNeighbours(initialCell[0], initialCell[1], map);
        if (nonEmptyNeighbours > 0)
            transform(map, initialCell);

        for (int i = 0; i < height; i++) {
            System.out.println(new String(map[i]));
        }
    }

    private static void transform(char[][] map, int[] initialCell) {
        int[] currentCell = new int[]{-1, -1};
        boolean started = false;
        while (currentCell[0] != initialCell[0] || currentCell[1] != initialCell[1]) {
            currentCell = gotToNextCell(started ? currentCell : initialCell);
            started = true;
            map[currentCell[0]][currentCell[1]] = ++map[currentCell[0]][currentCell[1]];
        }
    }

    private static int[] gotToNextCell(int[] currentCell) {
        currentDirection = sides.get(side).apply(currentDirection).previous;
        int[] nextCell = moves.get(currentDirection).apply(currentCell);
        while (!isWalkableCell(nextCell)) {
            currentDirection = sides.get(side).apply(currentDirection).next;
            nextCell = moves.get(currentDirection).apply(currentCell);
        }
        return nextCell;
    }

    private static boolean isWalkableCell(int[] cell) {
        final int i = cell[0];
        final int j = cell[1];
        if (i < 0 || j < 0) return false;
        if (i >= map.length || j >= map[i].length) return false;
        final char cellValue = map[i][j];
        return cellValue != '#';
    }

    static class Direction {
        final char dir;
        final char previous;
        final char next;

        public Direction(char dir, char previous, char next) {
            this.dir = dir;
            this.previous = previous;
            this.next = next;
        }

        static Direction of(char dir, char previous, char next) {
            return new Direction(dir, previous, next);
        }
    }

    private static int countNonEmptyNeighbours(int i, int j, char[][] map) {
        int res = 0;
        if (i > 0 && map[i - 1][j] != '#') res++;
        if (i < map.length - 1 && map[i + 1][j] != '#') res++;
        if (j > 0 && map[i][j - 1] != '#') res++;
        if (j < map[i].length - 1 && map[i][j + 1] != '#') res++;

        return res;
    }
}
