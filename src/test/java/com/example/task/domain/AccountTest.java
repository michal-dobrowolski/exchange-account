package com.example.task.domain;


import com.example.task.ApplicationException;
import com.example.task.Currency;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
class AccountTest {

    @Test
    public void shouldThrowExceptionWhenAccountUserIsNotLegalAge() {
        ApplicationException exception = Assert.assertThrows(
                ApplicationException.class, () -> prepareAccount("21230332424")
        );

        assertThat(exception.getMessage()).isEqualTo("To set up an account, a person should be of legal age.");
    }

    @Test
    public void shouldPassWhenAccountUserIsLegalAge() {
        Account account = prepareAccount("01230332424");

        assertThat(account).isNotNull();
    }

    private Account prepareAccount(String pesel) {
        return Account.builder()
                .pesel(pesel)
                .firstName("Zbyszek")
                .lastName("Zbyszkowicz")
                .subAccounts(
                        Map.of(
                                Currency.PLN.getCode(), new PLNSubAccount(BigDecimal.valueOf(1000L)),
                                Currency.USD.getCode(), new USDSubAccount(BigDecimal.ZERO)
                        )
                )
                .build();
    }

}