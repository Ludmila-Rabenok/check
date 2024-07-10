package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Order;
import main.java.ru.clevertec.check.model.OrderBuilder;
import main.java.ru.clevertec.check.service.DiscountCardService;
import main.java.ru.clevertec.check.service.OrderService;
import main.java.ru.clevertec.check.util.ParsingArgs;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final DiscountCardService discountCardService;
    private final static String DISCOUNT_CARD = "discountCard";
    private final static String BALANCE_DEBIT_CARD = "balanceDebitCard";
    private final static int INDEX_MAP_WITH_PRODUCT_ID_AND_QUANTITY = 0;
    private final static int INDEX_MAP_WITH_DISCOUNT_CARD = 1;
    private final static int INDEX_MAP_WITH_BALANCE_DEBIT_CARD = 2;


    public OrderServiceImpl(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @Override
    public Order createOrder(String[] args) {
        List<Map<String, String>> list = ParsingArgs.parsingArgsToListWithMaps(args);
        Order order;
        try {
            order = new OrderBuilder()
                    .setProductIdAndQuantityMap(getMapWithProductIdAndQuantity(list.get(INDEX_MAP_WITH_PRODUCT_ID_AND_QUANTITY)))
                    .setDiscountCard(getDiscountCard(list.get(INDEX_MAP_WITH_DISCOUNT_CARD)))
                    .setBalanceDebitCard(getBalanceDebitCard(list.get(INDEX_MAP_WITH_BALANCE_DEBIT_CARD)))
                    .build();
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }
        return order;
    }

    private Map<Integer, Integer> getMapWithProductIdAndQuantity(Map<String, String> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Integer.valueOf(e.getKey()), e -> Integer.valueOf(e.getValue())));

    }

    private DiscountCard getDiscountCard(Map<String, String> map) {
        if (map.isEmpty()) {
            return null;
        }
        return discountCardService.getByNumber(Integer.parseInt(map.get(DISCOUNT_CARD)));
    }

    private double getBalanceDebitCard(Map<String, String> map) {
        if (map.isEmpty()) {
            throw new BadRequestException();
        }
        double balance = Double.parseDouble(map.get(BALANCE_DEBIT_CARD));
        if (balance <= 0) {
            throw new NotEnoughMoneyException();
        }
        return balance;
    }
}