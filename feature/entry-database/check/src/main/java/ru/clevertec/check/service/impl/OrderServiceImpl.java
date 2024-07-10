package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.Order;
import ru.clevertec.check.model.OrderBuilder;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.OrderService;
import ru.clevertec.check.util.ParsingArgs;

public class OrderServiceImpl implements OrderService {
    private final DiscountCardService discountCardService;

    public OrderServiceImpl(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @Override
    public Order createOrder(String[] args) {
        return new OrderBuilder()
                .setProductIdAndQuantityMap(ParsingArgs.parseArgsToMapWithProductIdAndQuantity(args))
                .setDiscountCard(discountCardService.getByNumber(ParsingArgs.parseArgsToNumberDiscountCard(args)))
                .setBalanceDebitCard(ParsingArgs.parseArgsToBalanceDebitCard(args))
                .build();
    }
}