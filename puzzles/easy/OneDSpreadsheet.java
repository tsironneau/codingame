package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class OneDSpreadsheet {

    private static final Map<String, BiFunction<Integer, Integer, Integer>> operations = Map.of(
            "VALUE", (a, b) -> a,
            "ADD", (a, b) -> a + b,
            "SUB", (a, b) -> a - b,
            "MULT", (a, b) -> a * b
    );

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        final List<String[]> cells = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String operation = in.next();
            String arg1 = in.next();
            String arg2 = in.next();
            cells.add(new String[]{operation, arg1, arg2});
        }

        cells.forEach(cell -> System.out.println(cellValue(cell, cells)));
    }

    private static int cellValue(String[] cell, List<String[]> cells) {
        final var value = operations.get(cell[0]).apply(val(cell[1], cells), val(cell[2], cells));
        //To avoid recomputation of this cell, we change it to a "VALUE" cell type
        changeToValue(cell, value);
        return value;
    }

    private static int val(String rawValue, List<String[]> cells) {
        final var c = rawValue.charAt(0);
        if (c == '_') return 0;
        if (c != '$') return Integer.parseInt(rawValue);
        final var cellIndex = rawValue.substring(1);
        return cellValue(cells.get(Integer.parseInt(cellIndex)), cells);
    }

    private static void changeToValue(String[] cell, Integer value) {
        if (cell[0].equals("VALUE")) return;
        cell[0] = "VALUE";
        cell[1] = value.toString();
        cell[2] = "_";
    }
}
