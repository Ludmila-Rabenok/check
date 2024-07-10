package ru.clevertec.check.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class Order {

    private Map<Integer, Integer> productIdAndQuantityMap;
    private DiscountCard discountCard;
    private Double balanceDebitCard;
}
