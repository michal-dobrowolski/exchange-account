package com.example.task.infrastructure.client.nbp;

import com.example.task.domain.CurrencyRateProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
class NBPClient implements CurrencyRateProvider {

    private final WebClient nbpServiceWebClient;

    public Mono<BigDecimal> retrieveCurrencyRate(String currencyCode) {
        return nbpServiceWebClient.get().uri("/api/exchangerates/rates/a/{code}", currencyCode)
                .retrieve()
                .bodyToMono(NBPResponse.class)
                .map(this::getCurrencyRate);
    }

    private BigDecimal getCurrencyRate(NBPResponse nbpResponse) {
        return nbpResponse.getRates().get(0).getMid();
    }
}