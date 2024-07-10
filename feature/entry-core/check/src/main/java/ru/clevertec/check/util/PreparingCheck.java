package main.java.ru.clevertec.check.util;

import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.model.Position;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PreparingCheck {
    private static final String DELIMITER = ";";
    private static final String DOLLAR = "$";
    private static final String PERCENT = "%";
    private static final String DATE = "Date";
    private static final String TIME = "Time";
    private static final String QTY = "QTY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String PRICE = "PRICE";
    private static final String DISCOUNT = "DISCOUNT";
    private static final String TOTAL = "TOTAL";
    private static final String DISCOUNT_CARD = "DISCOUNT CARD";
    private static final String DISCOUNT_PERCENTAGE = "DISCOUNT PERCENTAGE";
    private static final String TOTAL_PRICE = "TOTAL PRICE";
    private static final String TOTAL_DISCOUNT = "TOTAL DISCOUNT";
    private static final String TOTAL_WITH_DISCOUNT = "TOTAL WITH DISCOUNT";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM.dd.yyyy");
    private static final DecimalFormat DOUBLE_FORMATTER = new DecimalFormat("#0.00");

    public static List<String> createLinesToPrintCheck(Check check) {
        List<String> list = new ArrayList<>();
        list.add(DATE + DELIMITER + TIME);
        list.add((DATE_FORMATTER.format(check.getDate())) + DELIMITER
                + (TIME_FORMATTER.format(check.getTime())));
        list.add("");
        list.add(QTY + DELIMITER
                + DESCRIPTION + DELIMITER
                + PRICE + DELIMITER
                + DISCOUNT + DELIMITER
                + TOTAL);
        list.addAll(createLinesWithPositions(check.getPositions()));
        if (!(check.getOrder().getDiscountCard() == null)) {
            list.addAll(createLinesWithDiscountCard(check));
        }
        list.add("");
        list.add(TOTAL_PRICE + DELIMITER
                + TOTAL_DISCOUNT + DELIMITER
                + TOTAL_WITH_DISCOUNT);
        list.add(DOUBLE_FORMATTER.format(check.getTotalPrice()) + DOLLAR + DELIMITER
                + DOUBLE_FORMATTER.format(check.getTotalDiscount()) + DOLLAR + DELIMITER
                + DOUBLE_FORMATTER.format(check.getTotalWithDiscount()) + DOLLAR);
        return list;
    }

    private static List<String> createLinesWithDiscountCard(Check check) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add(DISCOUNT_CARD + DELIMITER + DISCOUNT_PERCENTAGE);
        list.add(check.getOrder().getDiscountCard().getNumber() + DELIMITER
                + check.getOrder().getDiscountCard().getDiscountAmount() + PERCENT);
        return list;
    }

    private static List<String> createLinesWithPositions(List<Position> positions) {
        List<String> list = new ArrayList<>();
        for (Position position : positions) {
            list.add(position.getQuantity() + DELIMITER
                    + position.getProduct().getDescription() + DELIMITER
                    + DOUBLE_FORMATTER.format(position.getProduct().getPrice()) + DOLLAR + DELIMITER
                    + DOUBLE_FORMATTER.format(position.getDiscountPosition()) + DOLLAR + DELIMITER
                    + DOUBLE_FORMATTER.format(position.getTotalPricePosition()) + DOLLAR);
        }
        return list;
    }
}
