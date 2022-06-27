package com.example.task.infrastructure.database.mongo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceHistoryRepository extends ReactiveCrudRepository<BalanceHistoryDocument, String> {

}