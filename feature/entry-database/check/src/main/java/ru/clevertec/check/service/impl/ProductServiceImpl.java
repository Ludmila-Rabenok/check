package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.DB.ProductRepository;
import ru.clevertec.check.service.ProductService;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(int id) {
        return productRepository.readById(id);
    }

    @Override
    public Product create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public boolean update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public boolean delete(int id) {
        return productRepository.remove(id);
    }

    @Override
    public void changeQuantityInStock(Product product, int quantity) {
        productRepository.changeQuantityInStock(product.getId(),
                (product.getQuantityInStock()) - quantity);
    }
}
