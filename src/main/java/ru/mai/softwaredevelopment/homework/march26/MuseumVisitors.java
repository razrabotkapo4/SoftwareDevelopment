package ru.mai.softwaredevelopment.homework.march26;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MuseumVisitors {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        Scanner scanner = new Scanner(System.in);
//      2
        final int n = Integer.parseInt(scanner.nextLine());
        List<List<Object>> visitors = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
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

        int maxVisitors   = 0;
        int countVisitors = 0;
        Date startTimings = null;
        Date endTimings   = null;

        for (int i = 0; i < visitors.size(); ++i) {
            if (visitors.get(i).get(1).equals("start")) {
                ++countVisitors;
            } else {
                if (countVisitors > maxVisitors) {
                    maxVisitors   = countVisitors;
                    countVisitors = 0;
                    startTimings  = (Date) visitors.get(i - 1).get(0);
                    endTimings    = (Date) visitors.get(i).get(0);
                }
            }
        }

        System.out.print(simpleDateFormat.format(startTimings) + " " + simpleDateFormat.format(endTimings));
    }
}
