package ru.clevertec.check.service;


import ru.clevertec.check.model.DiscountCard;

public interface DiscountCardService {

    DiscountCard create(DiscountCard discountCard);

    DiscountCard getByNumber(int number);

    boolean update(DiscountCard discountCard);

    boolean delete(int id);
}
