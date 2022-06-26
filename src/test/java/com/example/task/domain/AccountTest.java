package com.example.task.domain;


import com.example.task.ApplicationException;
import com.example.task.api.AccountRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
class AccountTest {

    @Test
    public void shouldThrowExceptionWhenAccountUserIsNotLegalAge() {
        AccountRequest request = prepareAccountRequest();

        ApplicationException exception = Assert.assertThrows(ApplicationException.class, request::toDomain);

        assertThat(exception.getMessage()).isEqualTo("To set up an account, a person should be of legal age.");
    }
    //TODO::
    private AccountRequest prepareAccountRequest() {
        AccountRequest request = new AccountRequest();
        request.setPesel("21030332424");
        request.setFirstName("Zbyszek");
        request.setLastName("Zbyszkowicz");
        request.setPlnBalance(BigDecimal.valueOf(1000000L));
        return  request;
    }

}