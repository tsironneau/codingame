package medium;

import java.util.*;

/*
https://www.codingame.com/training/medium/skynet-revolution-episode-1
 */
public final class SkynetRevolutionEpOne {

    private static final Map<Integer, Node> NODES_MAP = new HashMap<>();
    private static final Set<Integer> EXITS = new HashSet<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        initNodesMap(N);
        final int L = in.nextInt(); // the number of links
        final int E = in.nextInt(); // the number of exit gateways
        buildNodeMap(in, L);
        buildExits(in, E);

        // game loop
        while (true) {
            final int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            final Link linkToDestroy = findLinkToDestroy(SI);
            removeLink(linkToDestroy);

            System.out.println(linkToDestroy.m_first + " " + linkToDestroy.m_second);
        }
    }

    private static void buildNodeMap(final Scanner in, final int L) {
        for (int i = 0; i < L; i++) {
            final int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            final int N2 = in.nextInt();
            NODES_MAP.get(N1).m_connectedNodes.add(N2);
            NODES_MAP.get(N2).m_connectedNodes.add(N1);
        }
    }

    private static void buildExits(final Scanner in, final int E) {
        for (int i = 0; i < E; i++) {
            final int EI = in.nextInt(); // the index of a gateway node
            EXITS.add(EI);
        }
    }

    private static Link findLinkToDestroy(final int skynetPos) {
        final Link nodeToDestroy = new Link();
        int minimumPathLength = Integer.MAX_VALUE;
        for (final int e : EXITS) {
            final List<Integer> path = findNearestPath(e, skynetPos);
            if (path == null)
                continue;

            if (path.isEmpty())
                continue;

            if (minimumPathLength > path.size()) {
                minimumPathLength = path.size();
                nodeToDestroy.m_first = path.get(path.size() - 2);
                nodeToDestroy.m_second = path.get(path.size() - 1);
            }
        }
        return nodeToDestroy;
    }

    //Breadth first search
    private static List<Integer> findNearestPath(final int start, final int end) {
        final Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        final Set<Integer> visitedNode = new HashSet<>();
        final Map<Integer, Integer> parentsMap = new HashMap<>();
        while (!queue.isEmpty()) {
            final int currentNodeId = queue.poll();
            visitedNode.add(currentNodeId);
            final Node node = NODES_MAP.get(currentNodeId);
            for (final int currentNeighbour : node.m_connectedNodes) {
                if (visitedNode.contains(currentNeighbour))
                    continue;
                queue.offer(currentNeighbour);
                parentsMap.put(currentNeighbour, currentNodeId);

                if (currentNeighbour == end)
                    return buildPath(currentNeighbour, parentsMap);
            }
        }

        return new LinkedList<>();
    }

    private static List<Integer> buildPath(final int end, final Map<Integer, Integer> parentsMap) {
        Integer currentNode = end;
        final List<Integer> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = parentsMap.get(currentNode);
        }
        System.err.println("Debug messages... path " + path);
        return path;
    }

    private static void removeLink(final Link link) {
        NODES_MAP.get(link.m_first).m_connectedNodes.remove(link.m_second);
        NODES_MAP.get(link.m_second).m_connectedNodes.remove(link.m_first);
    }

    private static void initNodesMap(final int N) {
        for (int i = 0; i < N; i++) {
            NODES_MAP.put(i, new Node(i));
        }
    }

    private static class Link {
        int m_first;
        int m_second;
    }

    private static class Node {
        final int m_id;
        final Set<Integer> m_connectedNodes = new HashSet<>();

        private Node(final int id) {
            m_id = id;
        }
    }
}
