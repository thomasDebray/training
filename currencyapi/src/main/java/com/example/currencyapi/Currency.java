package com.example.currencyapi;

public class Currency {
    private String code;
    private double exchangeRate;

    public Currency(){}

    public Currency(String code, double exchangeRate) {
        this.code = code;
        this.exchangeRate = exchangeRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
