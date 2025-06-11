package com.drill.currencyexchangeapp.repository;

import com.drill.currencyexchangeapp.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Spring Data JPA will auto-implement basic CRUD methods.
 * We add one custom finder for “latest” rates.
 */
public interface ExchangeRateRepository
        extends JpaRepository<ExchangeRate, Long> {

    /**
     * Finds the most recent ExchangeRate for a given pair.
     * Method name follows Spring Data conventions:
     *   findTopBy[Fields]OrderBy[Field]Desc
     */
    Optional<ExchangeRate> findTopByFromCurrencyAndToCurrencyOrderByTimestampDesc(
            String fromCurrency,
            String toCurrency
    );
}
