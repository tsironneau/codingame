package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
https://www.codingame.com/training/medium/the-last-crusade-episode-1
 */
public final class LastCrusadeEpOne {

    private final static Map<Integer, Room> ROOMS_DEFINITIONS = new HashMap<>();
    private static Room[][] MAP;

    public static void main(final String[] args) {
        createRoomsDefinitions();
        final Scanner in = new Scanner(System.in);
        buildMap(in);

        while (true) {
            final int XI = in.nextInt();
            final int YI = in.nextInt();
            final String POS = in.next();

            final Room currentRoom = MAP[XI][YI];
            final Direction nextDir = currentRoom.m_exitByEntrance.get(Direction.toDirection(POS));

            System.out.println((XI + nextDir.m_x) + " " + (YI + nextDir.m_y)); // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
        }
    }

    private static void buildMap(final Scanner in) {
        final int W = in.nextInt(); // number of columns.
        final int H = in.nextInt(); // number of rows.
        in.nextLine();
        MAP = new Room[W][H];

        for (int i = 0; i < H; i++) {
            final String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            final String[] split = LINE.split(" ");
            for (int j = 0; j < split.length; j++) {
                final String roomId = split[j];
                MAP[j][i] = ROOMS_DEFINITIONS.get(Integer.parseInt(roomId));
            }
        }
        in.nextInt();
    }

    private static void createRoomsDefinitions() {
        buildRoomZero();
        buildRoomOne();
        buildRoomTwo();
        buildRoomThree();
        buildRoomFour();
        buildRoomFive();
        buildRoomSix();
        buildRoomSeven();
        buildRoomEight();
        buildRoomNine();
        buildRoomTen();
        buildRoomEleven();
        buildRoomTwelve();
        buildRoomThirteen();
    }

    private static void buildRoomThirteen() {
        final Room currentRoom;
        currentRoom = new Room(13);
        currentRoom.addWays(Direction.LEFT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(13, currentRoom);
    }

    private static void buildRoomTwelve() {
        final Room currentRoom;
        currentRoom = new Room(12);
        currentRoom.addWays(Direction.RIGHT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(12, currentRoom);
    }

    private static void buildRoomEleven() {
        final Room currentRoom;
        currentRoom = new Room(11);
        currentRoom.addWays(Direction.TOP, Direction.RIGHT);
        ROOMS_DEFINITIONS.put(11, currentRoom);
    }

    private static void buildRoomTen() {
        final Room currentRoom;
        currentRoom = new Room(10);
        currentRoom.addWays(Direction.TOP, Direction.LEFT);
        ROOMS_DEFINITIONS.put(10, currentRoom);
    }

    private static void buildRoomNine() {
        final Room currentRoom;
        currentRoom = new Room(9);
        currentRoom.addWays(Direction.TOP, Direction.BOTTOM);
        currentRoom.addWays(Direction.LEFT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(9, currentRoom);
    }

    private static void buildRoomEight() {
        final Room currentRoom;
        currentRoom = new Room(8);
        currentRoom.addWays(Direction.LEFT, Direction.BOTTOM);
        currentRoom.addWays(Direction.RIGHT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(8, currentRoom);
    }

    private static void buildRoomSeven() {
        final Room currentRoom;
        currentRoom = new Room(7);
        currentRoom.addWays(Direction.TOP, Direction.BOTTOM);
        currentRoom.addWays(Direction.RIGHT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(7, currentRoom);
    }

    private static void buildRoomSix() {
        final Room currentRoom;
        currentRoom = new Room(6);
        currentRoom.addWays(Direction.RIGHT, Direction.LEFT);
        currentRoom.addWays(Direction.LEFT, Direction.RIGHT);
        ROOMS_DEFINITIONS.put(6, currentRoom);
    }

    private static void buildRoomFive() {
        final Room currentRoom;
        currentRoom = new Room(5);
        currentRoom.addWays(Direction.TOP, Direction.RIGHT);
        currentRoom.addWays(Direction.LEFT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(5, currentRoom);
    }

    private static void buildRoomFour() {
        final Room currentRoom;
        currentRoom = new Room(4);
        currentRoom.addWays(Direction.TOP, Direction.LEFT);
        currentRoom.addWays(Direction.RIGHT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(4, currentRoom);
    }

    private static void buildRoomThree() {
        final Room currentRoom;
        currentRoom = new Room(3);
        currentRoom.addWays(Direction.TOP, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(3, currentRoom);
    }

    private static void buildRoomTwo() {
        final Room currentRoom;
        currentRoom = new Room(2);
        currentRoom.addWays(Direction.RIGHT, Direction.LEFT);
        currentRoom.addWays(Direction.LEFT, Direction.RIGHT);
        ROOMS_DEFINITIONS.put(2, currentRoom);
    }

    private static void buildRoomOne() {
        final Room currentRoom;
        currentRoom = new Room(1);
        currentRoom.addWays(Direction.TOP, Direction.BOTTOM);
        currentRoom.addWays(Direction.LEFT, Direction.BOTTOM);
        currentRoom.addWays(Direction.RIGHT, Direction.BOTTOM);
        ROOMS_DEFINITIONS.put(1, currentRoom);
    }

    private static void buildRoomZero() {
        final Room currentRoom = new Room(0);
        ROOMS_DEFINITIONS.put(0, currentRoom);
    }

    private static class Room {
        final int m_id;
        final Map<Direction, Direction> m_exitByEntrance = new HashMap<>();

        Room(final int id) {
            this.m_id = id;
        }

        void addWays(final Direction entrance, final Direction exit) {
            m_exitByEntrance.put(entrance, exit);
        }
    }

    private enum Direction {
        TOP(0, -1),
        BOTTOM(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        final int m_x;
        final int m_y;

        Direction(final int m_x, final int m_y) {
            this.m_x = m_x;
            this.m_y = m_y;
        }

        public static Direction toDirection(final String pos) {
            return valueOf(pos);
        }

    }
}
