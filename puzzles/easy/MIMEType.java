package easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://www.codingame.com/training/easy/mime-type
 */
public final class MIMEType {
    private static final Map<String, String> EXT_TO_MIME = new HashMap<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt(); // Number of elements which make up the association table.
        final int Q = in.nextInt(); // Number Q of file names to be analyzed.

        for (int i = 0; i < N; i++) {
            final String EXT = in.next(); // file extension
            final String MT = in.next(); // MIME type.
            EXT_TO_MIME.put(EXT.toLowerCase(), MT);
            in.nextLine();
        }

        final String collect = IntStream.range(0, Q)
                .mapToObj(i -> nextLineToMime(in))
                .collect(Collectors.joining("\n"));
        System.out.println(collect);
    }

    private static String nextLineToMime(final Scanner in) {
        final String FNAME = in.nextLine();
        if (!FNAME.contains(".")) return "UNKNOWN";
        final String ext = FNAME.substring(FNAME.lastIndexOf(".") + 1).toLowerCase();
        final String mimeType = EXT_TO_MIME.get(ext);
        return mimeType == null ? "UNKNOWN" : mimeType;
    }
}
