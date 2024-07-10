package ru.clevertec.check.service;

import ru.clevertec.check.model.Position;

import java.util.List;

public interface PositionService {
    Position create(int productId, int quantity, int percentDiscountCard);

    void changeQuantityOfProductsInStock(List<Position> positions);
}
