package easy;

import java.util.Scanner;

public class PikapchaEp1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        char[][] map = new char[height][];
        for (int i = 0; i < height; i++) {
            map[i] = in.next().toCharArray();
        }

        transform(map);

        for (int i = 0; i < height; i++) {
            System.out.println(new String(map[i]));
        }
    }

    private static void transform(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            char[] line = map[i];
            for (int j = 0; j < line.length; j++) {
                final var c = line[j];
                if (c == '#') continue;
                line[j] = countNonEmptyNeighbours(i, j, map);
            }
        }
    }

    private static char countNonEmptyNeighbours(int i, int j, char[][] map) {
        int res = 0;
        if (i > 0 && map[i - 1][j] != '#') res++;
        if (i < map.length - 1 && map[i + 1][j] != '#') res++;
        if (j > 0 && map[i][j - 1] != '#') res++;
        if (j < map[i].length - 1 && map[i][j + 1] != '#') res++;

        return Character.forDigit(res, 10);
    }
}
