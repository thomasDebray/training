package com.example.currencyapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private List<Currency> currencies = new ArrayList<>();

    public List<Currency> getAllCurrencies() {
        return currencies;
    }

    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }

    public Currency getCurrencyByCode(String code) {
        return currencies.stream().filter(c -> c.getCode().equalsIgnoreCase(code))
        .findFirst().orElse(null);
    }

}
