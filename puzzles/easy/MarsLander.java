package easy;

import java.util.Scanner;

/*
 https://www.codingame.com/ide/puzzle/mars-lander-episode-1
 */
public final class MarsLander {

    private static final int MAX_LANDING_SPEED = -40;
    private static final int POWER_MAX = 4;

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        for (int i = 0; i < surfaceN; i++) {
            final int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            final int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
        }

        // game loop
        while (true) {
            final int X = in.nextInt();
            final int Y = in.nextInt();
            final int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            final int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            final int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            final int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).

            if (vSpeed <= MAX_LANDING_SPEED)
                power = Math.min(POWER_MAX, power + 1);
            else
                power = Math.max(0, power - 1);

            System.out.println("0 " + power);
        }
    }
}
