package com.example.task.domain;

import com.example.task.infrastructure.database.mongo.AccountDocument;
import com.example.task.infrastructure.database.mongo.BalanceHistoryDocument;
import com.example.task.infrastructure.database.mongo.BalanceHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BalanceHistoryService {

    private final BalanceHistoryRepository balanceHistoryRepository;

    public Mono<AccountDocument> create(AccountDocument accountDocument) {
        return balanceHistoryRepository.save(BalanceHistoryDocument.from(accountDocument))
                .thenReturn(accountDocument);
    }
}