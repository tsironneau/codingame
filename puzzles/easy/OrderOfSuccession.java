package easy;

import java.util.*;
import java.util.stream.Collectors;

/*
https://www.codingame.com/training/easy/order-of-succession
 */
public final class OrderOfSuccession {

    private static final Map<String, People> PEOPLES_MAP = new HashMap<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            final String name = in.next();
            final String parent = in.next();
            final int birth = in.nextInt();
            final String death = in.next();
            final String religion = in.next();
            final String gender = in.next();

            final People people = new People(name, parent, birth, death, religion, gender);
            PEOPLES_MAP.put(people.name, people);
        }

        buildTree();
        final List<People> succession = buildSuccession();

        succession.forEach(people -> System.out.println(people.name));
    }

    private static void buildTree() {
        PEOPLES_MAP.values().forEach(p -> {
            final People parent = PEOPLES_MAP.get((p.parent));
            if (parent != null)
                parent.addChild(p);
        });
    }

    private static List<People> buildSuccession() {
        final List<People> res = new ArrayList<>();

        final Optional<People> optionalRoot = PEOPLES_MAP.values().stream().filter(people -> people.parent.equals("-")).findAny();
        if (!optionalRoot.isPresent()) return res;

        final People root = optionalRoot.get();
        addSuccession(res, root);

        return res.stream()
                .filter(people -> people.death.equals("-") && !people.religion.equals("Catholic"))
                .collect(Collectors.toList());
    }

    private static void addSuccession(final List<People> res, final People parent) {
        res.add(parent);
        parent.children.forEach(c -> addSuccession(res, c));
    }

    private static class People {

        final String name;
        final String parent;
        final int birth;
        final String death;
        final String religion;
        final String gender;
        final List<People> children = new ArrayList<>();

        private People(final String name, final String parent, final int birth, final String death, final String religion, final String gender) {
            this.name = name;
            this.parent = parent;
            this.birth = birth;
            this.death = death;
            this.religion = religion;
            this.gender = gender;
        }

        void addChild(final People people) {
            children.add(people);
            children.sort(Comparator.comparing(People::getGender).reversed().thenComparingInt(People::getBirth));
        }

        int getBirth() {
            return birth;
        }

        String getGender() {
            return gender;
        }

        @Override
        public String toString() {
            final StringJoiner stringJoiner = new StringJoiner("\n\t", "\n\t", "");
            children.forEach(c -> stringJoiner.add(c.name));
            return "People{" +
                    "name='" + name + '\'' +
                    ", parent='" + parent + '\'' +
                    ", birth=" + birth +
                    ", death='" + death + '\'' +
                    ", religion='" + religion + '\'' +
                    ", gender='" + gender + '\'' +
                    ", children=\n\t" + children.stream().map(c -> c.name).collect(Collectors.toList()) +
                    '}';
        }
    }
}
