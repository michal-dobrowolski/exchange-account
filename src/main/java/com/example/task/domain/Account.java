package com.example.task.domain;

import com.example.task.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Builder
public class Account {

    private String id;

    private String pesel;

    private String lastName;

    private String firstName;

    private Map<String, SubAccount> subAccounts;

    private static Map<String, Integer> baseValue = new HashMap<>(
            Map.of(
                    "0", 1900,
                    "2", 2000,
                    "4", 2100,
                    "6", 2200,
                    "8", 1800
            )
    );

    public Account(String id, String pesel, String lastName, String firstName, Map<String, SubAccount> subAccounts) {
        validateAge(pesel);
        this.id = id;
        this.pesel = pesel;
        this.lastName = lastName;
        this.firstName = firstName;
        this.subAccounts = subAccounts;
    }

    public SubAccount getSubAccount(String currencyCode) {
        return getSubAccounts().get(currencyCode);
    }

    static void validateAge(String pesel) {
        String REGEX = "(?<=\\G.{2})";
        String[] date = pesel.substring(0, 6).split(REGEX);
        String key = date[1].substring(0, 1);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .optionalStart()
                .appendPattern("uuuu")
                .optionalEnd()
                .optionalStart()
                .appendValueReduced(ChronoField.YEAR, 2, 2, baseValue.get(key))
                .optionalEnd()
                .toFormatter();
        TemporalAccessor temporalAccessor = formatter.parse(date[0]);
        LocalDate birthday = LocalDate.of(
                temporalAccessor.get(ChronoField.YEAR),
                Integer.parseInt(date[1].substring(1, 2)),
                Integer.parseInt(date[2])
        );
        Period period = Period.between(birthday, today);
        if (period.getYears() < 18) {
            throw new ApplicationException("To set up an account, a person should be of legal age.");
        }
    }

}