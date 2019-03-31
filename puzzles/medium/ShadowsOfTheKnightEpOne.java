package medium;

import java.util.Scanner;

/*
https://www.codingame.com/training/medium/there-is-no-spoon-episode-1
 */
public final class ShadowsOfTheKnightEpOne {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int W = in.nextInt(); // width of the building.
        final int H = in.nextInt(); // height of the building.
        final int N = in.nextInt(); // maximum number of turns before game over.
        final int X0 = in.nextInt();
        final int Y0 = in.nextInt();

        int x = X0;
        int y = Y0;
        final Limit limit = new Limit(W - 1, H - 1);
        BombDir bombDir;
        System.err.println("Debug messages... limit " + limit);

        while (true) {
            final String BOMBDIR = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            bombDir = new BombDir(BOMBDIR);
            limit.update(bombDir, x, y);

            System.err.println("Debug messages... limit " + limit);

            x = findNextX(bombDir, limit, x);
            y = findNextY(bombDir, limit, y);

            System.out.println(x + " " + y); // the location of the next window Batman should jump to.
        }
    }

    private static int findNextY(final BombDir bombDir, final Limit limit, final int currentY) {
        final int nextY;
        if (bombDir.down) {
            int diff = Math.round((float) Math.abs(currentY - limit.maxY) / 2);
            diff = Math.max(diff, 1);//We want to go up at least by one
            nextY = currentY + diff;
        } else if (bombDir.up) {
            int diff = Math.round((float) Math.abs(currentY - limit.minY) / 2);
            diff = Math.max(diff, 1);//We want to go down at least by one
            nextY = currentY - diff;
        } else {
            nextY = currentY;//Bomb aligned we stay at the same coordinates
        }
        return clamp(nextY, limit.minY, limit.maxY);
    }

    private static int findNextX(final BombDir bombDir, final Limit limit, final int currentX) {
        final int nextX;
        if (bombDir.right) {
            int diff = Math.round((float) Math.abs(currentX - limit.maxX) / 2);
            diff = Math.max(diff, 1);//We want to go right at least by one
            nextX = currentX + diff;
        } else if (bombDir.left) {
            int diff = Math.round((float) Math.abs(currentX - limit.minX) / 2);
            diff = Math.max(diff, 1);//We want to go left at least by one
            nextX = currentX - diff;
        } else {
            nextX = currentX;//Bomb aligned we stay at the same coordinates
        }
        return clamp(nextX, limit.minX, limit.maxX);
    }

    private static int clamp(final int value, final int min, final int max) {
        return value <= min ? min : value >= max ? max : value;
    }

    private static class Limit {
        int minX;
        int maxX;
        int minY;
        int maxY;

        Limit(final int maxX, final int maxY) {
            this.maxX = maxX;
            this.maxY = maxY;
        }

        void update(final BombDir bombDir, final int X, final int Y) {
            if (bombDir.up) {
                maxY = Math.min(maxY, Y - 1);
            }
            if (bombDir.down) {
                minY = Math.max(minY, Y + 1);
            }
            if (bombDir.left) {
                maxX = Math.min(maxX, X - 1);
            }
            if (bombDir.right) {
                minX = Math.max(minX, X + 1);
            }

        }

        public String toString() {
            return minX + " " + maxX + " " + minY + " " + maxY;
        }
    }

    private static class BombDir {
        boolean up;
        boolean down;
        boolean right;
        boolean left;

        BombDir(final String bombDir) {
            up = bombDir.contains("U");
            down = bombDir.contains("D");
            right = bombDir.contains("R");
            left = bombDir.contains("L");
        }
    }
}
