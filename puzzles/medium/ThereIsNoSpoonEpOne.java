package medium;

import java.util.Arrays;
import java.util.Scanner;

/*
https://www.codingame.com/training/medium/there-is-no-spoon-episode-1
 */
public final class ThereIsNoSpoonEpOne {
    private static int[][] CHIPSET;

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);

        buildChipset(in);
        findAndDisplayNeighbours();
    }

    private static void findAndDisplayNeighbours() {
        for (int i = 0; i < CHIPSET.length; i++) {
            for (int j = 0; j < CHIPSET[i].length; j++) {
                if (CHIPSET[i][j] == 0)
                    continue;

                String toDisplay = j + " " + i;

                final int rightNeigbour = findNextRightIndex(j, CHIPSET[i]);
                if (rightNeigbour != -1)
                    toDisplay += " " + rightNeigbour + " " + i;
                else
                    toDisplay += " " + -1 + " " + -1;

                //Bottom neighbour
                final int bottomNeigbour = findNextBottomIndex(j, i, CHIPSET);
                if (bottomNeigbour != -1)
                    toDisplay += " " + j + " " + bottomNeigbour;
                else
                    toDisplay += " " + -1 + " " + -1;

                System.out.println(toDisplay); // Three coordinates: a node, its right neighbor, its bottom neighbor
            }
        }
    }

    private static void buildChipset(final Scanner in) {
        final int width = in.nextInt(); // the number of cells on the X axis
        final int height = in.nextInt(); // the number of cells on the Y axis
        CHIPSET = new int[height][width];
        in.nextLine();
        for (int i = 0; i < height; i++) {
            final String line = in.nextLine(); // width characters, each either 0 or .
            CHIPSET[i] = convertToArray(line);
            System.err.println("Debug messages... " + Arrays.toString(CHIPSET[i]));
        }
    }

    private static int findNextBottomIndex(final int columnIndex, final int index, final int[][] array) {
        if (columnIndex >= array[index].length)
            return -1;

        for (int i = index + 1; i < array.length; i++) {
            if (array[i][columnIndex] == 1)
                return i;
        }

        return -1;
    }

    private static int findNextRightIndex(final int index, final int[] line) {
        if (index >= line.length)
            return -1;

        for (int i = index + 1; i < line.length; i++) {
            if (line[i] == 1)
                return i;
        }

        return -1;
    }

    private static int[] convertToArray(final String line) {
        final int[] res = new int[line.length()];
        for (int i = 0; i < res.length; i++) {
            res[i] = line.charAt(i) == '0' ? 1 : 0;
        }
        return res;
    }
}
