package main.java.ru.clevertec.check.model;

import java.util.Map;

public class OrderBuilder {

    Map<Integer, Integer> productIdAndQuantityMap;
    DiscountCard discountCard;
    Double balanceDebitCard;

    public OrderBuilder() {
    }

    public OrderBuilder setProductIdAndQuantityMap(Map<Integer, Integer> productIdAndQuantityMap) {
        this.productIdAndQuantityMap = productIdAndQuantityMap;
        return this;
    }

    public OrderBuilder setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
        return this;
    }

    public OrderBuilder setBalanceDebitCard(Double balanceDebitCard) {
        this.balanceDebitCard = balanceDebitCard;
        return this;
    }

    public Order build() {
        return new Order(this);
    }
}
