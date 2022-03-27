package pl.allegro.tech.umk.productservice.repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

class ProductWithComplexKeyRepository implements ProductRepository {

    private final Map<ComplexKey, Product> products = new HashMap<>();

    @PostConstruct
    void initializeProducts() {
        products.put(new ComplexKey("123"), new Product("123", "IPhone 11", BigDecimal.valueOf(1000)));
        products.put(new ComplexKey("456"), new Product("456", "IPhone 12", BigDecimal.valueOf(2000)));
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(new ComplexKey(id)));
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    @Override
    public void addProduct(Product product) {
        products.put(new ComplexKey("key"), product);
    }

    static class ComplexKey {

        private final String key;

        ComplexKey(String key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ComplexKey customKey = (ComplexKey) o;
            return Objects.equals(key, customKey.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

    }

}
