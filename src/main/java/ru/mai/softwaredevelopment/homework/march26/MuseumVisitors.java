package ru.mai.softwaredevelopment.homework.march26;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MuseumVisitors {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        Scanner scanner = new Scanner(System.in);
//      2
        final int N = Integer.parseInt(scanner.nextLine());
        List<List<Object>> visitors = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
//          9:00 12:00
//          9:30 10:00
            String input = scanner.nextLine();
            String[] timings = input.split(" ");

            Date startTimings;
            Date endTimings;

            try {
                startTimings = simpleDateFormat.parse(timings[0]);
                endTimings   = simpleDateFormat.parse(timings[1]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            visitors.add(Arrays.asList(startTimings, "start"));
            visitors.add(Arrays.asList(endTimings, "end"));
        }

        scanner.close();

        visitors.sort(Comparator.comparing(timings -> (Date) timings.get(0)));

        Date startTimings = null;
        Date endTimings   = null;

        for (List<Object> visitor : visitors) {
            if (visitor.get(1).equals("start")) {
                startTimings = (Date) visitor.get(0);
            } else {
                endTimings   = (Date) visitor.get(0);

                break;
            }
        }

        System.out.print(simpleDateFormat.format(startTimings) + " - " + simpleDateFormat.format(endTimings));
    }
}
