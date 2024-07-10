package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.Check;
import ru.clevertec.check.model.CheckBuilder;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.model.Position;
import ru.clevertec.check.service.CheckService;
import ru.clevertec.check.service.OrderService;
import ru.clevertec.check.service.PositionService;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.util.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckServiceImpl implements CheckService {
    private final PositionService positionService;
    private final PrintService printService;

    public CheckServiceImpl(PositionService positionService, PrintService printService) {
        this.positionService = positionService;
        this.printService = printService;
    }

    @Override
    public Check createCheck(Order order) {
        List<Position> positions = createPositions(order);
        double totalPrice = countTotalPrice(positions);
        double totalDiscount = countTotalDiscount(positions);
        double totalWithDiscount = totalPrice - totalDiscount;
        Validation.validateBalanceDebitCard(order.getBalanceDebitCard(), totalWithDiscount);
        positionService.changeQuantityOfProductsInStock(positions);
        Check check= new CheckBuilder()
                .setOrder(order)
                .setPositions(positions)
                .setTotalPrice(totalPrice)
                .setTotalDiscount(totalDiscount)
                .setTotalWithDiscount(totalWithDiscount)
                .build();
        printService.exportCheckToCSV(check);
        return check;
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
}
