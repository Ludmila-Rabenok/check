package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.model.CheckBuilder;
import main.java.ru.clevertec.check.model.Order;
import main.java.ru.clevertec.check.model.Position;
import main.java.ru.clevertec.check.service.CheckService;
import main.java.ru.clevertec.check.service.OrderService;
import main.java.ru.clevertec.check.service.PositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckServiceImpl implements CheckService {
    private final OrderService orderService;
    private final PositionService positionService;

    public CheckServiceImpl(OrderService orderService, PositionService positionService) {
        this.orderService = orderService;
        this.positionService = positionService;
    }

    @Override
    public Check createCheck(String[] args) {
        Order order = orderService.createOrder(args);
        List<Position> positions = createPositions(order);
        double totalPrice = countTotalPrice(positions);
        double totalDiscount = countTotalDiscount(positions);
        double totalWithDiscount = totalPrice - totalDiscount;
        verifyBalanceDebitCard(order, totalWithDiscount);
        positionService.changeQuantityOfProductsInStock(positions);
        return new CheckBuilder()
                .setOrder(order)
                .setPositions(positions)
                .setTotalPrice(totalPrice)
                .setTotalDiscount(totalDiscount)
                .setTotalWithDiscount(totalWithDiscount)
                .build();
    }

    private List<Position> createPositions(Order order) {
        List<Position> positions = new ArrayList<>();
        Position position;
        int discountAmount = verifyExistDiscountCardAndReturnAmount(order);
        for (Map.Entry<Integer, Integer> entry : order.getProductIdAndQuantityMap().entrySet()) {
            position = positionService.create(entry.getKey()
                    , entry.getValue()
                    , discountAmount);
            positions.add(position);
        }
        return positions;
    }

    private int verifyExistDiscountCardAndReturnAmount(Order order) {
        if (order.getDiscountCard() == null) {
            return 0;
        } else return order.getDiscountCard().getDiscountAmount();
    }

    private double countTotalPrice(List<Position> positions) {
        return positions.stream()
                .map(Position::getTotalPricePosition)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private double countTotalDiscount(List<Position> positions) {
        return positions.stream()
                .map(Position::getDiscountPosition)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private void verifyBalanceDebitCard(Order order, double totalWithDiscount) {
        if (!(order.getBalanceDebitCard() >= totalWithDiscount)) {
            throw new NotEnoughMoneyException();
        }
    }
}
