package com.example.task.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Builder
public class BalanceHistory {

    private String id;

    private String pesel;

    private Map<String, SubAccount> subAccounts;
}