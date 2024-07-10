package ru.clevertec.check.util;

import ru.clevertec.check.exception.BadRequestException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class ParsingArgs {
    private static final String DISCOUNT_CARD = "discountCard";
    private static final String BALANCE_DEBIT_CARD = "balanceDebitCard";
    private static final String DATASOURCE_URL = "datasource.url";
    private static final String DATASOURCE_USERNAME = "datasource.username";
    private static final String DATASOURCE_PASSWORD = "datasource.password";
    private static final String DELIMITER_HYPHEN = "-";
    private static final String DELIMITER_EQUALS = "=";
    private static final int INDEX_0 = 0;
    private static final int INDEX_1 = 1;
    private static final String REGEX_INT_INT = "[0-9]+-[0-9]+";
    private static final String REGEX_INT4 = "\\d{4}";
    private static final String REGEX_DOUBLE = "([0-9]+)|([0-9]+\\.[0-9]+)";

    public static Map<Integer, Integer> parseArgsToMapWithProductIdAndQuantity(String[] args) {
//        return Arrays.stream(args)
//                .filter(arg -> arg.matches(REGEX_INT_INT))
//                .map(arg -> arg.split(DELIMITER_HYPHEN))
//                .filter(arg -> arg.length == 2)
//                .map(arg -> Map.entry(arg[INDEX_0], arg[INDEX_1]))
//                .collect(toMap(e -> Integer.valueOf(e.getKey()), e -> Integer.valueOf(e.getValue())));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        return map;
    }

    public static int parseArgsToNumberDiscountCard(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.startsWith(DISCOUNT_CARD))
                .map(arg -> arg.split(DELIMITER_EQUALS))
                .filter(arg -> arg.length == 2)
                .filter(arg -> arg[INDEX_1].matches(REGEX_INT4))
                .map(x -> Integer.valueOf(x[INDEX_1]))
                .findFirst().orElse(0);
    }

    public static double parseArgsToBalanceDebitCard(String[] args) {
        List<Double> list = Arrays.stream(args)
                .filter(arg -> arg.startsWith(BALANCE_DEBIT_CARD))
                .map(arg -> arg.split(DELIMITER_EQUALS))
                .filter(arg -> arg.length == 2)
                .filter(arg -> arg[INDEX_1].matches(REGEX_DOUBLE))
                .map(x -> Double.valueOf(x[INDEX_1]))
                .collect(toList());
        Validation.validateOneOnList(list);
        return list.get(INDEX_0);
    }

    public static String parseArgsToDatasourceURl(String[] args) {
        return parseArgsToDatasource(args, DATASOURCE_URL);
    }

    public static String parseArgsToDatasourceUsername(String[] args) {
        return parseArgsToDatasource(args, DATASOURCE_USERNAME);
    }

    public static String parseArgsToDatasourcePassword(String[] args) {
        return parseArgsToDatasource(args, DATASOURCE_PASSWORD);
    }

    private static String parseArgsToDatasource(String[] args, String name) {
        List<String> list = Arrays.stream(args)
                .filter(arg -> arg.startsWith(name))
                .map(arg -> arg.split(DELIMITER_EQUALS))
                .filter(arg -> arg.length == 2)
                .map(x -> x[INDEX_1])
                .collect(toList());
        Validation.validateOneOnList(list);
        return list.get(INDEX_0);
    }
}