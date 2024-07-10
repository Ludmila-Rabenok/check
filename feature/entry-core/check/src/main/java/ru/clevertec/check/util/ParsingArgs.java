package main.java.ru.clevertec.check.util;

import main.java.ru.clevertec.check.exception.BadRequestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsingArgs {
    private static final String DISCOUNT_CARD = "discountCard";
    private static final String BALANCE_DEBIT_CARD = "balanceDebitCard";
    private static final String DELIMITER_HYPHEN = "-";
    private static final String DELIMITER_EQUALS = "=";
    private static final int INDEX_0 = 0;
    private static final int INDEX_1 = 1;

    public static List<Map<String, String>> parsingArgsToListWithMaps(String[] args) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> mapWithProductIdAndQuantity = new HashMap<>();
        Map<String, String> mapWithDiscountCard = new HashMap<>();
        Map<String, String> mapWithBalanceDebitCard = new HashMap<>();
        for (String str : args) {
            String[] array;
            try {
                if (str.contains(DELIMITER_EQUALS)) {
                    array = str.split(DELIMITER_EQUALS);

                    if (array[INDEX_0].equals(DISCOUNT_CARD)) {
                        mapWithDiscountCard.put(array[INDEX_0], array[INDEX_1]);
                    } else if (array[INDEX_0].equals(BALANCE_DEBIT_CARD)) {
                        mapWithBalanceDebitCard.put(array[INDEX_0], array[INDEX_1]);
                    }
                }
                if (str.contains(DELIMITER_HYPHEN) && !str.contains(DELIMITER_EQUALS)) {
                    array = str.split(DELIMITER_HYPHEN);
                    mapWithProductIdAndQuantity.put(array[INDEX_0], array[INDEX_1]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BadRequestException();
            }
        }
        list.add(mapWithProductIdAndQuantity);
        list.add(mapWithDiscountCard);
        list.add(mapWithBalanceDebitCard);
        return list;
    }
}