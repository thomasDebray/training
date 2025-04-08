package com.example.currencyapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    
    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAllCurrencies();
    }
    
    @PostMapping
    public void addCurrency(@RequestBody Currency currency) {
        currencyService.addCurrency(currency);
    }

    @GetMapping("/{code}")
    public Currency getByCode(@PathVariable String code) {
        return currencyService.getCurrencyByCode(code);
    }
}
