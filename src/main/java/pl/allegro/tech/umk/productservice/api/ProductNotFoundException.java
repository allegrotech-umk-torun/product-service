package pl.allegro.tech.umk.productservice.api;

class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(String id) {
        super("There is no product with ID = " + id);
    }

}