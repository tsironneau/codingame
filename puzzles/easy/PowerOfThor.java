package easy;

import java.util.Scanner;

/*
https://www.codingame.com/training/easy/power-of-thor-episode-1
 */
public final class PowerOfThor {

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int lightX = in.nextInt(); // the X position of the light of power
        final int lightY = in.nextInt(); // the Y position of the light of power
        int thorX = in.nextInt(); // Thor's starting X position
        int thorY = in.nextInt(); // Thor's starting Y position

        while (true) {
            in.nextInt();

            final int diffX = lightX - thorX;
            final int diffY = lightY - thorY;

            String dirX = "";
            String dirY = "";

            if (diffX > 0) {
                thorX++;
                dirX = "E";
            } else if (diffX < 0) {
                thorX--;
                dirX = "W";
            }
            if (diffY > 0) {
                thorY++;
                dirY = "S";
            } else if (diffY < 0) {
                thorY--;
                dirY = "N";
            }

            System.out.println(dirY + dirX);
        }
    }
}
