package com.example.task.domain;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface AccountService {

    Mono<String> create(Account account);

    Mono<Account> getAccountByPesel(String pesel);

    Mono<Account> exchange(String pesel, String from, String to, BigDecimal amount);

}