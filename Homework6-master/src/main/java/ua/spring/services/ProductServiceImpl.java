package ua.spring.services;

import ua.spring.dao.ProductDao;
import ua.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void add(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.delete(UUID.randomUUID());
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.findOne(UUID.randomUUID());
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
}
