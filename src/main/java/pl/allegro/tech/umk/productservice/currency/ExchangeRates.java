package pl.allegro.tech.umk.productservice.currency;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

record ExchangeRates(String baseCurrency, ZonedDateTime currentDateTime, Map<String, BigDecimal> rates) {
}
