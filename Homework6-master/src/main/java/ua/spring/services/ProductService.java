package ua.spring.services;

import ua.spring.model.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);

    void update(Product product);

    void deleteById(Long id);

    Product getProductById(Long id);

    List<Product> getAllProducts();
}
