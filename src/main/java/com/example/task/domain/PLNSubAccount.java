package com.example.task.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@JsonDeserialize(as = PLNSubAccount.class)
public class PLNSubAccount extends SubAccount {

    private static final String CURRENCY_CODE = "PLN";

    private Currency currency = Currency.getInstance(CURRENCY_CODE);

    public PLNSubAccount(BigDecimal balance) {
        this.balance = balance;
    }

    PLNSubAccount() {
    }

    @Override
    String getCurrencyCode() {
        return currency.getCurrencyCode();
    }
}