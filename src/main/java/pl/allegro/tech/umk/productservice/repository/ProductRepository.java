package pl.allegro.tech.umk.productservice.repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);

    List<Product> findAll();

    void addProduct(Product product);

}
