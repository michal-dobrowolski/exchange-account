package com.example.task.api;

import com.example.task.domain.Account;
import com.example.task.domain.SubAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
class AccountResponse {

    private String id;

    private String pesel;

    private String lastName;

    private String firstName;

    private Map<String, SubAccount> subAccounts;

    static AccountResponse map(Account account) {
        return new AccountResponseBuilder()
                .id(account.getId())
                .pesel(account.getPesel())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .subAccounts(account.getSubAccounts())
                .build();
    }

}