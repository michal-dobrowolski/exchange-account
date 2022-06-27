package com.example.task;

public enum Currency {

    PLN("PLN"),
    USD("USD");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}