package com.example.task.infrastructure.database.mongo;

import com.example.task.domain.SubAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Setter
@Getter
@Document
@Builder
public class BalanceHistoryDocument {

    @Id
    private String id;

    private String pesel;

    private Map<String, SubAccount> subAccounts;

    public static BalanceHistoryDocument from(AccountDocument accountDocument) {
        return BalanceHistoryDocument.builder()
                .pesel(accountDocument.getPesel())
                .subAccounts(accountDocument.getSubAccounts())
                .build();
    }
}