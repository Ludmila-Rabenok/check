package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.Order;
import ru.clevertec.check.model.OrderBuilder;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.OrderService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final DiscountCardService discountCardService;

    public OrderServiceImpl(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @Override
    public Order createOrder(Map<Integer,Integer> map,int numberCard, double balance) {
        return new OrderBuilder()
                .setProductIdAndQuantityMap(map)
                .setDiscountCard(discountCardService.getByNumber(numberCard))
                .setBalanceDebitCard(balance)
                .build();
    }
}