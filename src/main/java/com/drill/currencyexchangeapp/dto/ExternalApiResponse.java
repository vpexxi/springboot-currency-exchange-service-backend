package com.drill.currencyexchangeapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Map;

public class ExternalApiResponse {

    @JsonProperty("conversion_rates")
    private Map<String, BigDecimal> conversionRates;

    // getters & setters
    public Map<String, BigDecimal> getConversionRates() {
        return conversionRates;
    }
    public void setConversionRates(Map<String, BigDecimal> conversionRates) {
        this.conversionRates = conversionRates;
    }
}
