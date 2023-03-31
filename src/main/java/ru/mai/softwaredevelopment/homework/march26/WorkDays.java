package ru.mai.softwaredevelopment.homework.march26;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WorkDays {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        final int rest = 4;

        Scanner scanner = new Scanner(System.in);
//      13.2.2021
        String input = scanner.nextLine();
        scanner.close();

        LocalDate startDate = LocalDate.parse(input, dateTimeFormatter);
        LocalDate endDate   = startDate.plusMonths(1);

        while (startDate.isBefore(endDate.plusDays(1))) {
            if (startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                startDate = startDate.plusDays(1);
            }

            System.out.println(startDate.format(dateTimeFormatter));

            startDate = startDate.plusDays(rest);
        }
    }
}
