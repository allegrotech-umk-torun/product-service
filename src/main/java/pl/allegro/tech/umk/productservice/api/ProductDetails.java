package pl.allegro.tech.umk.productservice.api;

import java.math.BigDecimal;

record ProductDetails(String id, String name, BigDecimal price, String currency) {
}
