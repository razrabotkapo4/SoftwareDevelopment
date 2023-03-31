package ru.mai.softwaredevelopment.homework.march26;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class TaskTimeTracking {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        final int millisPerSecond = 1000;
        final int secondsPerMinute = 60;
        final int minutesPerHour = 60;

        Scanner scanner = new Scanner(System.in);
//      [{"start":"25.01.2021 10:00:10"}, {"end":"25.01.2021 15:00:10"}, {"start":"25.01.2021 15:10:10"}, {"end":"25.01.2021 16:40:40"}]
        String input = scanner.nextLine();
        scanner.close();

        input = input.replaceAll("[\\[\\]{}\"'`;]", "")
                     .replaceAll("start: ", "")
                     .replaceAll("end: ", "")
                     .replaceAll("start:", "")
                     .replaceAll("end:", "");
        String[] datesList = input.split(", ");

        long millis = 0;

        for (int i = 0; i < datesList.length; i += 2) {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate   = Calendar.getInstance();

            try {
                startDate.setTime(simpleDateFormat.parse(datesList[i]));
                endDate.setTime(simpleDateFormat.parse(datesList[i + 1]));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            millis += (endDate.getTimeInMillis() - startDate.getTimeInMillis());
        }

        long hours = millis / (millisPerSecond * secondsPerMinute * minutesPerHour);
        long minutes = (millis / (millisPerSecond * secondsPerMinute)) - (hours * minutesPerHour);

        System.out.print(hours + "-" + minutes);
    }
}
