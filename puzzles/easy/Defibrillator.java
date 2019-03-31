package easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
https://www.codingame.com/training/easy/defibrillators
 */
public final class Defibrillator {

    private static final List<Defib> DEFIBS = new ArrayList<>();

    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final String LON = in.next();
        final String LAT = in.next();
        final int N = in.nextInt();
        in.nextLine();

        IntStream.range(0, N).forEach(
                i -> DEFIBS.add(buildDefib(in.nextLine()))
        );

        final float longitude = Float.parseFloat(LON.replace(',', '.'));
        final float latitude = Float.parseFloat(LAT.replace(',', '.'));

        final Defib defib = findNearestDefib(longitude, latitude);

        System.out.println(defib.m_name);
    }

    private static Defib findNearestDefib(final float longitude, final float latitude) {
        return DEFIBS.parallelStream()
                .min(Comparator.comparing(a -> computeDistance(a, longitude, latitude)))
                .orElse(null);
    }

    private static double computeDistance(final Defib defib, final float longitude, final float latitude) {
        final double x = (longitude - defib.m_longitude) * Math.cos((defib.m_latitude + latitude) / 2);
        final double y = latitude - defib.m_latitude;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * 6371;
    }

    private static Defib buildDefib(final String definition) {
        final String[] splittedDef = definition.split(";");
        final Defib res = new Defib();
        res.m_id = Integer.parseInt(splittedDef[0]);
        res.m_name = splittedDef[1];
        res.m_address = splittedDef[2];
        res.m_tel = splittedDef[3];
        res.m_longitude = Float.parseFloat(splittedDef[4].replace(',', '.'));
        res.m_latitude = Float.parseFloat(splittedDef[5].replace(',', '.'));
        return res;
    }

    private static class Defib {
        int m_id;
        String m_name;
        String m_address;
        String m_tel;
        float m_longitude;
        float m_latitude;
    }
}
