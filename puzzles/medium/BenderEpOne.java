package medium;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

/*
https://www.codingame.com/training/medium/bender-episode-1
 */
public final class BenderEpOne {

    private static final char START = '@';
    private static final char END = '$';
    private static final char EMPTY = ' ';
    private static final char BORDER = '#';
    private static final char OBSTACLE = 'X';
    private static final char BIER = 'B';
    private static final char INVERTER = 'I';
    private static final char TELEPORT = 'T';
    private static final char NORTH = 'N';
    private static final char SOUTH = 'S';
    private static final char EAST = 'E';
    private static final char WEST = 'W';

    private static char[][] MAP;
    private static Point ENDING_POINT;
    private static Point CURRENT_POINT;
    private static Point TELEPORT_1 = null;
    private static Point TELEPORT_2 = null;

    private static final State NORMAL_STATE = new NormalState();
    private static final State INVERTED_STATE = new InvertedState();

    private static State CURRENT_STATE = NORMAL_STATE;
    private static Direction CURRENT_DIRECTION = Direction.SOUTH;
    private static boolean BREAK_OBSTACLES = false;

    private static final Set<String> EXPLORED_MAP_COMPOSITION = new HashSet<>();

    public static void main(final String[] args) {
        buildMap();

        final String path = buildPath();
        System.out.println(path);
    }

    private static String buildPath() {
        final StringJoiner res = new StringJoiner("\n");

        while (!arrived()) {
            triggerCellEffect();
            updateDirection();
            updateCurrentPoint();
            breakObstacles();
            final String composition = buildMapComposition();
            if (loop(composition))
                return "LOOP";
            addExploredMapComposition(composition);
            res.add(CURRENT_DIRECTION.name());
        }

        return res.toString();
    }

    private static void triggerCellEffect() {
        final char c = getCurrentCell();
        switch (c) {
            case INVERTER:
                invertCurrentState();
                break;
            case BIER:
                invertBreakObstacles();
                break;
            case TELEPORT:
                teleport();
                break;
            case NORTH:
                CURRENT_DIRECTION = Direction.NORTH;
                break;
            case SOUTH:
                CURRENT_DIRECTION = Direction.SOUTH;
                break;
            case EAST:
                CURRENT_DIRECTION = Direction.EAST;
                break;
            case WEST:
                CURRENT_DIRECTION = Direction.WEST;
                break;
        }
    }

    private static void teleport() {
        if (TELEPORT_1.m_x == CURRENT_POINT.m_x && TELEPORT_1.m_y == CURRENT_POINT.m_y)
            CURRENT_POINT.set(TELEPORT_2);
        else
            CURRENT_POINT.set(TELEPORT_1);
    }

    private static void breakObstacles() {
        final char c = getCurrentCell();
        if (c == OBSTACLE)
            MAP[CURRENT_POINT.m_x][CURRENT_POINT.m_y] = EMPTY;
    }

    private static void updateCurrentPoint() {
        CURRENT_POINT.m_x = CURRENT_POINT.m_x + CURRENT_DIRECTION.m_x;
        CURRENT_POINT.m_y = CURRENT_POINT.m_y + CURRENT_DIRECTION.m_y;
    }

    private static void invertCurrentState() {
        if (CURRENT_STATE == NORMAL_STATE)
            CURRENT_STATE = INVERTED_STATE;
        else if (CURRENT_STATE == INVERTED_STATE)
            CURRENT_STATE = NORMAL_STATE;
    }

    private static void invertBreakObstacles() {
        BREAK_OBSTACLES = !BREAK_OBSTACLES;
    }

    private static boolean arrived() {
        return CURRENT_POINT.m_x == ENDING_POINT.m_x && CURRENT_POINT.m_y == ENDING_POINT.m_y;
    }

    private static void updateDirection() {
        CURRENT_STATE.updateDirection();
    }

    private static boolean loop(final String composition) {
        return EXPLORED_MAP_COMPOSITION.contains(composition);
    }

    private static void buildMap() {
        final Scanner in = new Scanner(System.in);
        final int L = in.nextInt();
        final int C = in.nextInt();
        in.nextLine();
        MAP = new char[C][L];
        for (int i = 0; i < L; i++) {
            final String row = in.nextLine();
            final char[] chars = row.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                final char aChar = chars[j];
                MAP[j][i] = aChar;
                if (aChar == START) {
                    CURRENT_POINT = new Point(i, j);
                } else if (aChar == END) {
                    ENDING_POINT = new Point(i, j);
                } else if (aChar == TELEPORT) {
                    if (TELEPORT_1 == null)
                        TELEPORT_1 = new Point(i, j);
                    else
                        TELEPORT_2 = new Point(i, j);
                }
            }
        }
        addExploredMapComposition(buildMapComposition());
    }

    private static void addExploredMapComposition(final String s) {
        EXPLORED_MAP_COMPOSITION.add(s);
    }

    private static String buildMapComposition() {
        final StringBuilder res = new StringBuilder(CURRENT_DIRECTION + CURRENT_POINT.toString() + BREAK_OBSTACLES + CURRENT_STATE);
        for (final char[] chars : MAP) {
            res.append(new String(chars));
        }
        return res.toString();
    }

    private static char getCurrentCell() {
        return MAP[CURRENT_POINT.m_x][CURRENT_POINT.m_y];
    }

    private interface State {
        void updateDirection();
    }

    private static class NormalState implements State {

        @Override
        public void updateDirection() {
            char nextStep = MAP[CURRENT_POINT.m_x + CURRENT_DIRECTION.m_x][CURRENT_POINT.m_y + CURRENT_DIRECTION.m_y];
            if (nextStep != BORDER && (BREAK_OBSTACLES || nextStep != OBSTACLE))
                return;

            final Direction[] values = Direction.values();
            for (final Direction direction : values) {
                nextStep = MAP[CURRENT_POINT.m_x + direction.m_x][CURRENT_POINT.m_y + direction.m_y];
                if (nextStep == BORDER)
                    continue;
                else if (!BREAK_OBSTACLES && nextStep == OBSTACLE)
                    continue;
                CURRENT_DIRECTION = direction;
                return;
            }
        }
    }

    private static class InvertedState implements State {

        @Override
        public void updateDirection() {
            char nextStep = MAP[CURRENT_POINT.m_x + CURRENT_DIRECTION.m_x][CURRENT_POINT.m_y + CURRENT_DIRECTION.m_y];
            if (nextStep != BORDER && (BREAK_OBSTACLES || nextStep != OBSTACLE))
                return;

            final Direction[] values = Direction.values();
            for (int i = values.length - 1; i >= 0; i--) {
                final Direction direction = values[i];
                nextStep = MAP[CURRENT_POINT.m_x + direction.m_x][CURRENT_POINT.m_y + direction.m_y];
                if (nextStep == BORDER)
                    continue;
                else if (!BREAK_OBSTACLES && nextStep == OBSTACLE)
                    continue;
                CURRENT_DIRECTION = direction;
                return;
            }
        }
    }

    private enum Direction {
        SOUTH(0, 1),
        EAST(1, 0),
        NORTH(0, -1),
        WEST(-1, 0)
        //
        ;

        private final int m_x;
        private final int m_y;

        Direction(final int x, final int y) {
            m_x = x;
            m_y = y;
        }
    }

    private static class Point {
        int m_x;
        int m_y;

        Point(final int y, final int x) {
            m_y = y;
            m_x = x;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Point point = (Point) o;

            if (m_x != point.m_x) return false;
            return m_y == point.m_y;

        }

        @Override
        public int hashCode() {
            int result = m_x;
            result = 31 * result + m_y;
            return result;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "m_x=" + m_x +
                    ", m_y=" + m_y +
                    '}';
        }

        void set(final Point point) {
            m_x = point.m_x;
            m_y = point.m_y;
        }
    }

}
