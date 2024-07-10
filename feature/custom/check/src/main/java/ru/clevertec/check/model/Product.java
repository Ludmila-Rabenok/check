package ru.clevertec.check.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private int id;
    private String description;
    private double price;
    private int quantityInStock;
    private boolean wholesaleProduct;
}
