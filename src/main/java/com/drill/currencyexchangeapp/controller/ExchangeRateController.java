package com.drill.currencyexchangeapp.controller;

import com.drill.currencyexchangeapp.model.ExchangeRate;
import com.drill.currencyexchangeapp.service.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

/**
 * REST controller exposing currency exchange endpoints.
 */
@RestController  // Marks this class as a REST API handler
@RequestMapping("/api")  // Base path for all endpoints
public class ExchangeRateController {

    private final ExchangeRateService service;

    /**
     * Constructor-based DI to inject the service layer.
     */
    public ExchangeRateController(ExchangeRateService service) {
        this.service = service;
    }

    /**
     * A simple ping endpoint to verify controller is loaded.
     */
    @GetMapping("/ping")
    public String ping() {
        return "pong";  // used to confirm the controller is scanned
    }

    /**
     * Fetch live exchange rate, store it in DB, and return the saved record.
     * HTTP 201 created on success.
     */
    @PostMapping("/rates/fetch")
    @ResponseStatus(HttpStatus.CREATED)
    public ExchangeRate fetchAndStore(
            @RequestParam String from,
            @RequestParam String to
    ) {
        // Normalize currency codes to uppercase
        return service.fetchAndStoreRate(
                from.toUpperCase(),
                to.toUpperCase()
        );
    }

    /**
     * Retrieve the most recent stored exchange rate for a currency pair.
     */
    @GetMapping("/rates/latest")
    public ExchangeRate getLatest(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return service.getLatestRate(
                from.toUpperCase(),
                to.toUpperCase()
        );
    }

    /**
     * Convert an amount from one currency to another using the stored rate.
     */
    @GetMapping("/convert")
    public BigDecimal convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount
    ) {
        return service.convert(
                from.toUpperCase(),
                to.toUpperCase(),
                amount
        );
    }
}
