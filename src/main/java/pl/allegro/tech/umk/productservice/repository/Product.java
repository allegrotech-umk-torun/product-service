package pl.allegro.tech.umk.productservice.repository;

import java.math.BigDecimal;

public record Product(String id, String name, BigDecimal price) {
}
