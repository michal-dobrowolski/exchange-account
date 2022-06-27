package com.example.task.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@JsonDeserialize(as = USDSubAccount.class)
public class USDSubAccount extends SubAccount {

    protected Currency currency = Currency.getInstance(com.example.task.Currency.USD.getCode());

    public USDSubAccount(BigDecimal balance) {
        this.balance = balance;
    }

    public USDSubAccount() {
    }

    @Override
    String getCurrencyCode() {
        return currency.getCurrencyCode();
    }
}