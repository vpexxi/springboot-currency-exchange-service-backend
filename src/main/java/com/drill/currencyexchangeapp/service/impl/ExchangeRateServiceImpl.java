package com.drill.currencyexchangeapp.service.impl;

import com.drill.currencyexchangeapp.dto.ExternalApiResponse;
import com.drill.currencyexchangeapp.exception.RateNotFoundException;      // ← import this
import com.drill.currencyexchangeapp.model.ExchangeRate;
import com.drill.currencyexchangeapp.repository.ExchangeRateRepository;
import com.drill.currencyexchangeapp.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository repository;
    private final WebClient webClient;
    private final String apiKey;

    public ExchangeRateServiceImpl(
            ExchangeRateRepository repository,
            @Value("${exchange.api.url}") String apiUrl,
            @Value("${exchange.api.key}") String apiKey) {
        this.repository = repository;
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
    }

    @Override
    public ExchangeRate fetchAndStoreRate(String from, String to) {
        ExternalApiResponse response = webClient.get()
                .uri("/{key}/latest/{base}", apiKey, from)
                .retrieve()
                .bodyToMono(ExternalApiResponse.class)
                .block();

        BigDecimal rate = response.getConversionRates().get(to);
        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency: " + to);
        }

        ExchangeRate entity = new ExchangeRate(from, to, rate, Instant.now());
        return repository.save(entity);
    }

    @Override
    public ExchangeRate getLatestRate(String from, String to) {
        return repository
                .findTopByFromCurrencyAndToCurrencyOrderByTimestampDesc(from, to)
                // ← make sure this line uses your custom exception:
                .orElseThrow(() -> new RateNotFoundException(from, to));
    }

    @Override
    public BigDecimal convert(String from, String to, BigDecimal amount) {
        BigDecimal rate = getLatestRate(from, to).getRate();
        return amount.multiply(rate);
    }
}
