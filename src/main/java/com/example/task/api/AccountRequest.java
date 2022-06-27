package com.example.task.api;

import com.example.task.Currency;
import com.example.task.domain.Account;
import com.example.task.domain.PLNSubAccount;
import com.example.task.domain.USDSubAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class AccountRequest {

    @NotEmpty
    private String pesel;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstName;

    @NotNull
    private BigDecimal plnBalance;

    public Account toDomain() {
        return Account.builder()
                .pesel(this.getPesel())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .subAccounts(
                        Map.of(
                                Currency.PLN.getCode(), new PLNSubAccount(this.getPlnBalance()),
                                Currency.USD.getCode(), new USDSubAccount(BigDecimal.valueOf(0L))
                        )
                ).build();
    }

}