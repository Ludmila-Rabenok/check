package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.model.Position;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.repository.CSV.ProductRepositoryCSV;
import main.java.ru.clevertec.check.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryCSV productRepositoryCSV;

    public ProductServiceImpl(ProductRepositoryCSV productRepositoryCSV) {
        this.productRepositoryCSV = productRepositoryCSV;
    }

    @Override
    public Product getById(int id) {
        Product product;
        try {
            product = productRepositoryCSV.getById(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }
        return product;
    }

    @Override
    public void changeQuantityInStock(List<Position> positions) {
        productRepositoryCSV.changeQuantityInStock(positions);
    }
}
