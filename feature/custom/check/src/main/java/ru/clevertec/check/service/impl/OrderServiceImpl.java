package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.Order;
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
        return Order.builder()
                .productIdAndQuantityMap(map)
                .discountCard(discountCardService.getByNumber(numberCard))
                .balanceDebitCard(balance)
                .build();
    }
}