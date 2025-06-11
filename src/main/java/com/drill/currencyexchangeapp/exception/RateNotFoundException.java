package com.drill.currencyexchangeapp.exception;

/**
 * Thrown when a requested currency pair has no stored exchange rate.
 */
public class RateNotFoundException extends RuntimeException {
    public RateNotFoundException(String from, String to) {
        super("No exchange rate found for " + from + " → " + to);
    }
}
