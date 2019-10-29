package com.homeedition.reservation.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateService {

    /**
     * Generate days of week
     *
     * @param today current day
     * @return list of days from monday to sunday
     */
    public static ArrayList<LocalDate> daysOfWeek(LocalDate today) {
        ArrayList<LocalDate> daysOfWeek = new ArrayList<>();
        LocalDate monday = getFirstDay(today);
        daysOfWeek.add(monday);
        while (monday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            daysOfWeek.add(monday.plusDays(1));
            monday = monday.plusDays(1);
        }
        return daysOfWeek;
    }

    /**
     * Generate list hour of day
     *
     * @param onlyHour times without minutes
     * @return list of time
     */
    public static List<LocalTime> timesOfDay(boolean onlyHour) {
        int additionalTime = onlyHour ? 30 : 0;
        int additionalRange = onlyHour ? 1 : 2;
        LocalTime baseTime = LocalTime.of(0, 0);
        return IntStream.range(0, 24 * additionalRange)
                .mapToObj(i -> baseTime.plusMinutes((additionalTime + 30) * i)).collect(Collectors.toList());
    }

    /**
     * Get monday of week
     *
     * @param today current day
     * @return monday
     */
    private static LocalDate getFirstDay(LocalDate today) {
        LocalDate monday = today;
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }
        return monday;
    }



}