package ru.clevertec.check.util;

import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.model.Product;

import java.util.List;

public class Validation {

    public static void validateProduct(Product product, int quantity) {
        if (product == null) {
            throw new BadRequestException();
        } else if (!(product.getQuantityInStock() >= quantity)) {
            throw new BadRequestException();
        }
    }

    public static void validateBalanceDebitCard(Double balanceDebitCard, double totalWithDiscount) {
        if (!(balanceDebitCard >= totalWithDiscount)) {
            throw new NotEnoughMoneyException();
        }
    }

    public static void validateOneOnList(List list) {
        if (!(list.size() == 1)) {
            throw new BadRequestException();
        }
    }
}