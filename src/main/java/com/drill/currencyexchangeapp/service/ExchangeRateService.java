package com.drill.currencyexchangeapp.service;

import com.drill.currencyexchangeapp.model.ExchangeRate;
import java.math.BigDecimal;

/**
 * Defines business operations for currency rates and conversions.
 */
public interface ExchangeRateService {

    /**
     * Fetches the latest rate for a currency pair from the external API,
     * stores it in the database, and returns the saved entity.
     */
    ExchangeRate fetchAndStoreRate(String fromCurrency, String toCurrency);

    /**
     * Retrieves the most recently stored rate for the given currency pair.
     * Throws if no rate is found.
     */
    ExchangeRate getLatestRate(String fromCurrency, String toCurrency);

    /**
     * Converts an amount from one currency to another using the
     * latest stored exchange rate.
     */
    BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount);
}
