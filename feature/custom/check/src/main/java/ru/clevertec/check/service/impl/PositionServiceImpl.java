package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.Position;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.PositionService;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.util.Validation;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    private final ProductService productService;
    private static final int AUCTION_DISCOUNT_10_PERCENT = 10;
    private static final int PERCENT_100 = 100;
    private static final int QUANTITY_FOR_DISCOUNT = 5;

    public PositionServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Position create(int productId, int quantity, int percentDiscountCard) {
        Product product = productService.getById(productId);
        Validation.validateProduct(product, quantity);
        double totalPrice = countTotalPrice(product, quantity);
        double discount = countDiscount(product, quantity, totalPrice, percentDiscountCard);
        return new Position(product, quantity, totalPrice, discount);
    }

    @Override
    public int changeQuantityOfProductsInStock(List<Position> positions) {
        int count = 0;
        for (Position position : positions) {
            if (productService.changeQuantityInStock(position.getProduct(), position.getQuantity())) {
                count++;
            }
        }return count;
    }

    private double countDiscount(Product product, Integer quantity, double totalPrice, int percentDiscountCard) {
        if (product.isWholesaleProduct() && quantity >= QUANTITY_FOR_DISCOUNT) {
            return totalPrice * AUCTION_DISCOUNT_10_PERCENT / PERCENT_100;
        } else return totalPrice * percentDiscountCard / PERCENT_100;
    }

    private double countTotalPrice(Product product, int quantity) {
        return product.getPrice() * quantity;
    }

}
