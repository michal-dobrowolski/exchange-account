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

    private Currency currency = Currency.getInstance(com.example.task.Currency.PLN.getCode());

    public PLNSubAccount(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    String getCurrencyCode() {
        return currency.getCurrencyCode();
    }
}