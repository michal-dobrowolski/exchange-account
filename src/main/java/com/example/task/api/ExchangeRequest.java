package com.example.task.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
class ExchangeRequest {

    @NotEmpty
    private String from;

    @NotEmpty
    private String to;

    @NotNull
    private BigDecimal amount;

}