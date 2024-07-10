package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.Position;
import main.java.ru.clevertec.check.model.Product;

import java.util.List;

public interface ProductService {

    Product getById(int id);

    void changeQuantityInStock(List<Position> positions);
}
