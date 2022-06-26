package com.example.task.domain;

import com.example.task.ApplicationException;
import com.example.task.domain.Account;
import com.example.task.domain.ExchangeService;
import com.example.task.domain.PLNSubAccount;
import com.example.task.domain.USDSubAccount;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
class ExchangeServiceTest {

    ExchangeService exchangeService = new ExchangeService();

    @Test
    public void shouldExchangeMoneyFromPlnToUsdAccordingToRate() {
        Account account = prepareAccount(BigDecimal.ZERO);

        Account accountAfterExchange = exchangeService.exchange(
                account, "PLN", "USD", BigDecimal.valueOf(100L), BigDecimal.valueOf(4.5)
        );

        assertThat(accountAfterExchange.getSubAccount("PLN").getBalance()).isEqualTo(BigDecimal.valueOf(100L));
        assertThat(accountAfterExchange.getSubAccount("USD").getBalance()).isEqualTo(BigDecimal.valueOf(22.22));
    }

    @Test
    public void shouldExchangeMoneyFromUsdToPlnAccordingToRate() {
        Account account = prepareAccount(BigDecimal.valueOf(100L));

        Account accountAfterExchange = exchangeService.exchange(
                account, "USD", "PLN", BigDecimal.valueOf(100L), BigDecimal.valueOf(4.5)
        );

        assertThat(accountAfterExchange.getSubAccount("PLN").getBalance()).isEqualTo(BigDecimal.valueOf(650.0));
        assertThat(accountAfterExchange.getSubAccount("USD").getBalance()).isEqualTo(BigDecimal.valueOf(0L));
    }

    @Test
    public void shouldThrowExceptionWhenBalanceExceedZero() {
        Account account = prepareAccount(BigDecimal.valueOf(50));

        ApplicationException exception = Assert.assertThrows(ApplicationException.class, () -> exchangeService.exchange(
                account, "USD", "PLN", BigDecimal.valueOf(100L), BigDecimal.valueOf(4.5)
        ));

        assertThat(exception.getMessage()).isEqualTo("Insufficient funds to exchange.");
    }

    private Account prepareAccount(BigDecimal usd) {
        return new Account.AccountBuilder()
                .pesel("72010133333")
                .firstName("Robert")
                .lastName("Makłowicz")
                .subAccounts(
                        Map.of(
                                "PLN", new PLNSubAccount(BigDecimal.valueOf(200L)),
                                "USD", new USDSubAccount(usd)
                        )
                )
                .build();
    }

}