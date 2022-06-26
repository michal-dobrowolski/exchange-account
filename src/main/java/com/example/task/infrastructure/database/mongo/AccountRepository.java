package com.example.task.infrastructure.database.mongo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountDocument, String> {

    Mono<AccountDocument> getByPesel(String pesel);

}