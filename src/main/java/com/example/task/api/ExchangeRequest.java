package com.example.task.api;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
class ExchangeRequest {

    private String from;

    private String to;

    private BigDecimal amount;

}