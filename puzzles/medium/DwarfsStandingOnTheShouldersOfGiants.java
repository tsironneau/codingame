package medium;

import java.util.*;

/*
https://www.codingame.com/training/medium/dwarfs-standing-on-the-shoulders-of-giants
 */
public final class DwarfsStandingOnTheShouldersOfGiants {

    private static final Map<Integer, Node> NODES_MAP = new HashMap<>();

    public static void main(final String[] args) {
        buildNodeMap();

        final int longestPath = NODES_MAP
                .values()
                .stream()
                .mapToInt(DwarfsStandingOnTheShouldersOfGiants::longestPathFrom)
                .max()
                .orElse(0);

        System.out.println(longestPath);
    }

    private static int longestPathFrom(final Node a) {
        return longestPathFrom(a.m_id, new HashSet<>());
    }

    private static int longestPathFrom(final Integer a, final Set<Integer> visitedNodes) {
        visitedNodes.add(a);
        int longestPath = 0;
        final Node node = NODES_MAP.get(a);
        if (node == null)
            return 1;
        for (final Integer nextNode : node.m_connectedNodes) {
            if (visitedNodes.contains(nextNode))
                continue;
            visitedNodes.add(nextNode);
            final int pathLength = longestPathFrom(nextNode, visitedNodes);
            if (pathLength > longestPath)
                longestPath = pathLength;
            visitedNodes.remove(nextNode);
        }
        return longestPath + 1;
    }

    private static void buildNodeMap() {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt(); // the number of relationships of influence
        for (int i = 0; i < n; i++) {
            final int x = in.nextInt();
            final int y = in.nextInt();
            Node node = NODES_MAP.get(x);
            if (node == null) {
                node = new Node(x);
                NODES_MAP.put(x, node);
            }
            node.m_connectedNodes.add(y);
        }
    }

    private static class Node {
        final int m_id;
        final Set<Integer> m_connectedNodes = new HashSet<>();

        private Node(final int id) {
            m_id = id;
        }
    }

}
