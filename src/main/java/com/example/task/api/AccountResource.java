package com.example.task.api;

import com.example.task.domain.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AccountResource {
//nowy commit
    private final AccountService accountService;
    //featF
    @PostMapping("/accounts")
    public Mono<String> create(@Valid @RequestBody AccountRequest request) {
        return accountService.create(request.toDomain());
    }
    //featX
//featB - poprawione - poprawka
    @GetMapping("/accounts/{pesel}")
    public Mono<AccountResponse> getAccountByPesel(@PathVariable("pesel") String pesel) {
        return accountService.getAccountByPesel(pesel).map(AccountResponse::map);
    }

    @PostMapping("/accounts/{pesel}/exchange")
    public Mono<AccountResponse> exchange(@PathVariable("pesel") String pesel, @RequestBody ExchangeRequest request) {
        return accountService.exchange(pesel, request.getFrom(), request.getTo(), request.getAmount()).map(AccountResponse::map);
    }

}