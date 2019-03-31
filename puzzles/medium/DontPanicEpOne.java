package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
https://www.codingame.com/training/medium/don't-panic-episode-1
 */
public final class DontPanicEpOne {

    private static final Map<Integer, Integer> ELEVATOR_POS_BY_FLOOR = new HashMap<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        initData(in);
        // game loop
        while (true) {
            final int cloneFloor = in.nextInt(); // floor of the leading clone
            final int clonePos = in.nextInt(); // position of the leading clone on its floor
            final String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            if (noCloneToMove(cloneFloor, clonePos, direction)) {
                System.out.println("WAIT"); // action: WAIT or BLOCK
                continue;
            }

            if (isInApproriateDirection(cloneFloor, clonePos, direction)) {
                System.out.println("WAIT");
                continue;
            }

            System.out.println("BLOCK");
        }
    }

    private static void initData(final Scanner in) {
        final int nbFloors = in.nextInt(); // number of floors
        final int width = in.nextInt(); // width of the area
        final int nbRounds = in.nextInt(); // maximum number of rounds
        final int exitFloor = in.nextInt(); // floor on which the exit is found
        final int exitPos = in.nextInt(); // position of the exit on its floor
        final int nbTotalClones = in.nextInt(); // number of generated clones
        final int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        final int nbElevators = in.nextInt(); // number of elevators
        for (int i = 0; i < nbElevators; i++) {
            final int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            final int elevatorPos = in.nextInt(); // position of the elevator on its floor
            ELEVATOR_POS_BY_FLOOR.put(elevatorFloor, elevatorPos);
        }
        ELEVATOR_POS_BY_FLOOR.put(exitFloor, exitPos);
    }

    private static boolean isInApproriateDirection(final int cloneFloor, final int clonePos, final String direction) {
        final int elevatorPos = ELEVATOR_POS_BY_FLOOR.get(cloneFloor);
        if (elevatorPos > clonePos && "LEFT".equals(direction))
            return false;
        if (elevatorPos < clonePos && "RIGHT".equals(direction))
            return false;
        return true;
    }

    private static boolean noCloneToMove(final int cloneFloor, final int clonePos, final String direction) {
        return cloneFloor == -1 && clonePos == -1 && "NONE".equals(direction);
    }
}
