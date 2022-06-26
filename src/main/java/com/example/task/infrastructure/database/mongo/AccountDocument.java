package com.example.task.infrastructure.database.mongo;

import com.example.task.domain.Account;
import com.example.task.domain.SubAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Document
@Builder
public class AccountDocument {

    @Id
    private String id;

    @Indexed(unique = true)
    private String pesel;

    private String lastName;

    private String firstName;

    private Map<String, SubAccount> subAccounts;

    public static AccountDocument from(Account account) {
        return new AccountDocumentBuilder()
                .id(account.getId())
                .pesel(account.getPesel())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .subAccounts(account.getSubAccounts())
                .build();
    }

    public Account toDomain() {
        return Account.builder()
                .id(this.getId())
                .pesel(this.getPesel())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .subAccounts(this.getSubAccounts())
                .build();
    }

}