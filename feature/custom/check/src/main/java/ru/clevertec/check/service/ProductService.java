package ru.clevertec.check.service;

import ru.clevertec.check.model.Product;

public interface ProductService {

    Product create(Product product);

    Product getById(int id);

    boolean update(Product product);

    boolean delete(int id);

    boolean changeQuantityInStock(Product product, int quantity);
}
