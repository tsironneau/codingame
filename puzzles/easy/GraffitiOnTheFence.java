package easy;

import java.util.*;

public class GraffitiOnTheFence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int N = in.nextInt();

        List<Pair<Integer, Integer>> paintedSections = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int st = in.nextInt();
            int ed = in.nextInt();

            paintedSections.add(Pair.of(st, ed));
        }

        sortPaintedSections(paintedSections);
        final var mergedPaintedSections = mergePaintedSections(paintedSections);

        final var unpaintedSections = toUnpaintedSections(mergedPaintedSections, L);
        printSections(unpaintedSections);
    }

    private static void sortPaintedSections(final List<Pair<Integer, Integer>> paintedSections) {
        paintedSections.sort(Comparator.comparing(Pair::getFirst));
    }

    private static List<Pair<Integer, Integer>> mergePaintedSections(List<Pair<Integer, Integer>> paintedSections) {
        Iterator<Pair<Integer, Integer>> it = paintedSections.iterator();
        if (!it.hasNext()) return Collections.emptyList();

        List<Pair<Integer, Integer>> res = new ArrayList<>();
        Pair<Integer, Integer> currentSection = null;
        while (it.hasNext()) {
            final var next = it.next();
            if (currentSection == null) currentSection = next;

            if (overlaps(currentSection, next)) {
                currentSection = merge(currentSection, next);
            } else {
                res.add(currentSection);
                currentSection = next;
            }
        }

        res.add(currentSection);

        return res;
    }

    private static Pair<Integer, Integer> merge(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
        return Pair.of(Math.min(first.getFirst(), second.getFirst()), Math.max(first.getSecond(), second.getSecond()));
    }

    private static boolean overlaps(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
        if (first == null || second == null) return false;
        final var first1 = first.getFirst();
        final var second1 = first.getSecond();

        final var first2 = second.getFirst();
        final var second2 = second.getSecond();

        if (first2 >= first1 && first2 <= second1) return true;
        if (second2 >= first1 && second2 <= second1) return true;

        if (first1 >= first2 && first1 <= second2) return true;
        if (second1 >= first2 && second1 <= second2) return true;

        return false;
    }

    private static List<Pair<Integer, Integer>> toUnpaintedSections(List<Pair<Integer, Integer>> sections, int max) {
        final var res = new ArrayList<Pair<Integer, Integer>>();
        int currentIndex = 0;
        for (Pair<Integer, Integer> section : sections) {
            if (0 != section.getFirst()) res.add(Pair.of(currentIndex, section.getFirst()));
            currentIndex = section.getSecond();
        }

        if (currentIndex != max) res.add(Pair.of(currentIndex, max));

        return res;
    }

    private static void printSections(List<Pair<Integer, Integer>> sections) {
        if (sections.isEmpty()) System.out.println("All painted");
        else sections.forEach(s ->
                System.out.println(String.format("%d %d", s.getFirst(), s.getSecond()))
        );
    }

    private static void printDebugSections(List<Pair<Integer, Integer>> sections) {
        sections.forEach(s ->
                System.err.println(String.format("%d %d", s.getFirst(), s.getSecond()))
        );
    }


    private static final class Pair<A, B> {

        private final A first;
        private final B second;

        private Pair(final A first, final B second) {
            this.first = first;
            this.second = second;
        }

        public static <A, B> Pair<A, B> of(final A first, final B second) {
            return new Pair<>(first, second);
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

        public String toString() {
            return this.first + " " + this.second;
        }

    }
}