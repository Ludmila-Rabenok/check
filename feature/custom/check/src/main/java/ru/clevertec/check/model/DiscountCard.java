package ru.clevertec.check.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountCard {

    private int id;
    private int number;
    private int discountAmount;
}
