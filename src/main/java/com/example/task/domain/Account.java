package com.example.task.domain;

import com.example.task.ApplicationException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Setter
@Getter
@Builder(access = AccessLevel.PUBLIC)
public class Account {

    private String id;

    private String pesel;

    private String lastName;

    private String firstName;

    private Map<String, SubAccount> subAccounts;

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

    private void validateAge(String pesel) {
        String date = pesel.substring(0, 6);
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyMMdd"));
        Period period = Period.between(birthday, today);
        if (period.getYears() < 18) {
            throw new ApplicationException("To set up an account, a person should be of legal age.");
        }
    }

}