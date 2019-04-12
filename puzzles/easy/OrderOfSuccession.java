package easy;

import java.util.Scanner;

/*
https://www.codingame.com/training/easy/order-of-succession
 */
public final class OrderOfSuccession {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String parent = in.next();
            int birth = in.nextInt();
            String death = in.next();
            String religion = in.next();
            String gender = in.next();
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("orDeroFsucceSsion");
    }
}
