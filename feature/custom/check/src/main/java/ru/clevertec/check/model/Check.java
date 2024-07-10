package ru.clevertec.check.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
public class Check {
    private LocalDate date;
    private LocalTime time;
    private Order order;
    private List<Position> positions;
    private double totalPrice;
    private double totalDiscount;
    private double totalWithDiscount;
}
