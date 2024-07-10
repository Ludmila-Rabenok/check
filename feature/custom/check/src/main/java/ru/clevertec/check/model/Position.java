package ru.clevertec.check.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private Product product;
    private int quantity;
    private double totalPricePosition;
    private double discountPosition;
}
