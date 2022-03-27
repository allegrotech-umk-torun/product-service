package pl.allegro.tech.umk.productservice.currency;

import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record CurrencyConverter(RestTemplate restTemplate) {

    private static final String EXCHANGE_RATES_SERVICE_URL = "http://localhost:8086/exchange-rates/latest?base=";

    public BigDecimal convertPrice(BigDecimal price, String baseCurrency, String targetCurrency) {
        ExchangeRates exchangeRates = getExchangeRatesFor(baseCurrency);
        BigDecimal targetRate = exchangeRates.rates().get(targetCurrency);
        if (targetRate == null) {
            throw new InvalidExchangeRateException(targetCurrency);
        }
        return price.multiply(targetRate).setScale(2, RoundingMode.DOWN);
    }

    private ExchangeRates getExchangeRatesFor(String baseCurrency) {
        return restTemplate.getForEntity(EXCHANGE_RATES_SERVICE_URL + baseCurrency, ExchangeRates.class).getBody();
    }

}
