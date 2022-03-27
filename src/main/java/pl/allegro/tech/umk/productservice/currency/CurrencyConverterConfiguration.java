package pl.allegro.tech.umk.productservice.currency;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class CurrencyConverterConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    CurrencyConverter currencyConverter(RestTemplate restTemplate) {
        return new CurrencyConverter(restTemplate);
    }

}