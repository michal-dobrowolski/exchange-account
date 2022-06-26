package com.example.task.infrastructure.client.nbp;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Rate {

    private String no;

    private LocalDate effectiveDate;

    private BigDecimal mid;

}