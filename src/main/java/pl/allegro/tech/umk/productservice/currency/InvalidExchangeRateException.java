package pl.allegro.tech.umk.productservice.currency;

public class InvalidExchangeRateException extends RuntimeException {

    InvalidExchangeRateException(String currency) {
        super(String.format("Invalid exchange rate for %s", currency));
    }

}