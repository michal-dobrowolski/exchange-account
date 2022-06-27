package com.example.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

public class PeselUtil {

    private static final Map<String, Integer> centuries = new HashMap<>(
            Map.of(
                    "0", 1900,
                    "1", 1900,
                    "2", 2000,
                    "3", 2000,
                    "4", 2100,
                    "5", 2100,
                    "6", 2200,
                    "7", 2200,
                    "8", 1800,
                    "9", 1800
            )
    );

   static public LocalDate getBirthday(String pesel) {
        String splitByPairsRegex = "(?<=\\G.{2})";
        String[] date = getDateFromPesel(pesel).split(splitByPairsRegex);
        String centuryKey = getCenturyKey(date);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .optionalStart()
                .appendPattern("uuuu")
                .optionalEnd()
                .optionalStart()
                .appendValueReduced(ChronoField.YEAR, 2, 2, centuries.get(centuryKey))
                .optionalEnd()
                .toFormatter();
        TemporalAccessor parsedYear = formatter.parse(date[0]);
        return LocalDate.of(
                parsedYear.get(ChronoField.YEAR),
                getMonth(date, centuryKey),
                getDayOfMonth(date)
        );
    }

    private static int getDayOfMonth(String[] date) {
        return Integer.parseInt(date[2]);
    }

    private static int getMonth(String[] date, String centuryKey) {
        return Integer.parseInt(centuryKey) % 2 == 0 ?
                Integer.parseInt(date[1].substring(1, 2)) :
                Integer.parseInt(date[1].substring(1, 2)) + 10;
    }

    private static String getCenturyKey(String[] date) {
        return date[1].substring(0, 1);
    }

    private static String getDateFromPesel(String pesel) {
        return pesel.substring(0, 6);
    }

}