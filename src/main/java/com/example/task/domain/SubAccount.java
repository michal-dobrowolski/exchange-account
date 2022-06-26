package com.example.task.domain;

import com.example.task.ApplicationException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonDeserialize(as = PLNSubAccount.class)
public abstract class SubAccount {

    protected BigDecimal balance;

    public SubAccount() {
    }

    abstract String getCurrencyCode();

    void addToBalance(BigDecimal value) {
        setBalance(this.balance.add(value));
    }

    void subtractFromBalance(BigDecimal value) {
        BigDecimal result = this.balance.subtract(value);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new ApplicationException("Insufficient funds to exchange.");
        }
        setBalance(result);
    }

}