package com.example.task.domain;

import com.example.task.infrastructure.database.mongo.AccountDocument;
import com.example.task.infrastructure.database.mongo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class AccountServiceImp implements AccountService {

    private final ExchangeService exchangeService;

    private final AccountRepository accountRepository;

    private final CurrencyRateProvider currencyRateProvider;

    private final BalanceHistoryService balanceHistoryService;

    @Override
    public Mono<String> create(Account account) {
        return accountRepository.save(AccountDocument.from(account))
                .flatMap(balanceHistoryService::create)
                .map(AccountDocument::getId);
    }

    @Override
    public Mono<Account> getAccountByPesel(String pesel) {
        return accountRepository.getByPesel(pesel).map(AccountDocument::toDomain);
    }

    @Override
    public Mono<Account> exchange(String pesel, String from, String to, BigDecimal amount) {
        return getAccountByPesel(pesel).zipWith(
                        currencyRateProvider.retrieveCurrencyRate("USD"),
                        (account, currencyRate) -> exchangeService.exchange(account, from, to, amount, currencyRate)
                ).flatMap(account -> accountRepository.save(AccountDocument.from(account)))
                .flatMap(balanceHistoryService::create)
                .map(AccountDocument::toDomain);
    }

}