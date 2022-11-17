package com.example.task.api;

import com.example.task.domain.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AccountResource {

    private final AccountService accountService;
//tutaj ktos cos domergowal
    @PostMapping("/accounts")
    public Mono<String> create(@Valid @RequestBody AccountRequest request) {
        return accountService.create(request.toDomain());
    }

    @GetMapping("/accounts/{pesel}")
    public Mono<AccountResponse> getAccountByPesel(@PathVariable("pesel") String pesel) {
        return accountService.getAccountByPesel(pesel).map(AccountResponse::map);
    }

    @PostMapping("/accounts/{pesel}/exchange")
    public Mono<AccountResponse> exchange(@PathVariable("pesel") String pesel, @RequestBody ExchangeRequest request) {
        return accountService.exchange(pesel, request.getFrom(), request.getTo(), request.getAmount()).map(AccountResponse::map);
    }

}