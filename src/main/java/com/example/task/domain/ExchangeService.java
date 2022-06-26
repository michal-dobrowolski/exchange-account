package com.example.task.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class ExchangeService {

    Account exchange(Account account, String from, String to, BigDecimal amount, BigDecimal currencyRate) {
        SubAccount fromSubAccount = account.getSubAccount(from);
        SubAccount toSubAccount = account.getSubAccount(to);
        fromSubAccount.subtractFromBalance(amount);
        if (fromSubAccount.getCurrencyCode().equals("PLN")) {
            toSubAccount.addToBalance(amount.divide(currencyRate, 2, RoundingMode.HALF_UP));
        } else {
            toSubAccount.addToBalance(amount.multiply(currencyRate));
        }
        return account;
    }

}