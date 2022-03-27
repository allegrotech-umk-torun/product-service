package pl.allegro.tech.umk.productservice.repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class ProductWithSimpleKeyRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    @PostConstruct
    void initializeProducts() {
        products.put("123", new Product("123", "IPhone 11", BigDecimal.valueOf(1000)));
        products.put("456", new Product("456", "IPhone 12", BigDecimal.valueOf(2000)));
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    @Override
    public void addProduct(Product product) {
        products.put(product.id(), product);
    }

    private void addSampleProducts() {
        for (int i = 0; i < 100000; i++) {
            addProduct(new Product(String.valueOf(i), "IPhone", BigDecimal.valueOf(Math.random() * 1000)));
        }
    }

}
