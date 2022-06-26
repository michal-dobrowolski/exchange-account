package com.example.task.domain;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface CurrencyRateProvider {

    Mono<BigDecimal> retrieveCurrencyRate(String currencyCode);

}