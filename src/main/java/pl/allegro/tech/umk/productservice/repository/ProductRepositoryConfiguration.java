package pl.allegro.tech.umk.productservice.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductRepositoryConfiguration {

    @Bean
    ProductRepository productRepository() {
        return new ProductWithSimpleKeyRepository();
    }

}