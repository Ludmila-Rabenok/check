package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.model.Position;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.service.PositionService;
import main.java.ru.clevertec.check.service.ProductService;

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
        double totalPrice = countTotalPrice(product, quantity);
        Position position = new Position();
        position.setProduct(product);
        position.setQuantity(quantity);
        position.setTotalPricePosition(totalPrice);
        position.setDiscountPosition(countDiscount(product, quantity, totalPrice, percentDiscountCard));
        return position;
    }

    @Override
    public void changeQuantityOfProductsInStock(List<Position> positions) {
        verifyQuantityInStock(positions);
        productService.changeQuantityInStock(positions);
    }

    private void verifyQuantityInStock(List<Position> positions) {
        for (Position position : positions) {
            if (!(position.getProduct().getQuantityInStock() >= position.getQuantity())) {
                throw new InternalServerException();
            }
        }
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
