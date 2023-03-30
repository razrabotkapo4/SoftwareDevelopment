package ru.mai.softwaredevelopment.homework.march26;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class TaskTimeTracking {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");

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

        long hours = millis / (1000 * 60 * 60);
        long minutes = (millis / (1000 * 60)) - (hours * 60);

        System.out.print(hours + "-" + minutes);
    }
}
