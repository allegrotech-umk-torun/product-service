package pl.allegro.tech.umk.productservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.allegro.tech.umk.productservice.currency.CurrencyConverter;
import pl.allegro.tech.umk.productservice.repository.Product;
import pl.allegro.tech.umk.productservice.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

@RestController
record Endpoints(ProductRepository productRepository, CurrencyConverter currencyConverter) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Endpoints.class);

    @GetMapping("/product/{id}")
    ResponseEntity<ProductDetails> getProductDetails(@PathVariable String id, @RequestParam(required = false) String currency) {
        Supplier<ProductDetails> request = () -> productRepository.findById(id)
                .map(product -> toProductDetails(product, currency))
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ResponseEntity.ok(execute(request));
    }

    @GetMapping("/products/totalPrice")
    ResponseEntity<String> getTotalPrice() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(computeTotalPrice(products).toString());
    }

    @PostMapping("/product/samples")
    ResponseEntity<String> addSampleProducts() {
        for (int i = 0; i < 1000; i++) {
            productRepository.addProduct(new Product(String.valueOf(i), "IPhone", BigDecimal.valueOf(1000)));
        }
        LOGGER.info("Added sample products");
        return ResponseEntity.noContent().build();
    }

    private ProductDetails toProductDetails(Product product, String currency) {
        if (currency != null) {
            BigDecimal targetPrice = currencyConverter.convertPrice(product.price(), "PLN", currency);
            return new ProductDetails(product.id(), product.name(), targetPrice, currency);
        } else {
            return new ProductDetails(product.id(), product.name(), product.price(), "PLN");
        }
    }

    private ProductDetails execute(Supplier<ProductDetails> supplier) {
        long startTime = System.currentTimeMillis();
        ProductDetails response = supplier.get();
        Object[] arguments = new Object[]{response.id(), response.currency(), System.currentTimeMillis() - startTime};
        LOGGER.info("Fetched product details: ID = {}, currency = {}, duration = {}ms", arguments);
        return response;
    }

    private BigDecimal computeTotalPrice(List<Product> products) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products) {
            sum = sum.add(product.price());
        }
        return sum;
    }

}
