package ru.clevertec.check.repository.DB;

import ru.clevertec.check.model.Product;

public interface ProductRepository {

    Product create(Product product);

    Product readById(int id);

    boolean update(Product product);

    boolean remove(int id);

    boolean changeQuantityInStock(int id, int quantityInStock);
}
