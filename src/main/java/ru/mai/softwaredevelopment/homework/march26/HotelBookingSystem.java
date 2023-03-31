package ru.mai.softwaredevelopment.homework.march26;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HotelBookingSystem {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.M.yyyy");

        Scanner scanner = new Scanner(System.in);
//      ["12.09.2020", "14.09.2020-02.10.2020"]
        String bookingInput     = scanner.nextLine();
//      01.10.2020-05.10.2020
        String testBookingInput = scanner.nextLine();
        scanner.close();

        bookingInput = bookingInput.replaceAll("[\\[\\]\" '`;]", "");
        String[] bookingDatesList = bookingInput.split(", ");

        String[] testBookingDatesRange = testBookingInput.split("-");
        LocalDate startTestBookingDatesRange = LocalDate.parse(testBookingDatesRange[0], dateTimeFormatter);
        LocalDate endTestBookingDatesRange   = testBookingDatesRange.length == 1 ? startTestBookingDatesRange
                                               : LocalDate.parse(testBookingDatesRange[1], dateTimeFormatter);

        for (String bookingDates : bookingDatesList) {
            if (bookingDates.contains("-")) {
                String[] bookingDatesRange = bookingDates.split("-");
                LocalDate startBookingDatesRange = LocalDate.parse(bookingDatesRange[0], dateTimeFormatter);
                LocalDate endBookingDatesRange   = LocalDate.parse(bookingDatesRange[1], dateTimeFormatter);

                for (LocalDate testBookingDate = startTestBookingDatesRange;
                     testBookingDate.isBefore(endTestBookingDatesRange.plusDays(1));
                     testBookingDate = testBookingDate.plusDays(1)) {
                    if (testBookingDate.isAfter(startBookingDatesRange)
                        && testBookingDate.isBefore(endBookingDatesRange)) {
                        System.out.print(false);

                        return;
                    }
                }
            } else {
                LocalDate singleBookingDate = LocalDate.parse(bookingDates, dateTimeFormatter);

                for (LocalDate testBookingDate = startTestBookingDatesRange;
                     testBookingDate.isBefore(endTestBookingDatesRange.plusDays(1));
                     testBookingDate = testBookingDate.plusDays(1)) {
                    if (testBookingDate.equals(singleBookingDate)) {
                        System.out.print(false);

                        return;
                    }
                }
            }
        }

        System.out.print(true);
    }
}
