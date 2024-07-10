package ru.clevertec.check.service;


import ru.clevertec.check.model.DiscountCard;

public interface DiscountCardService {

    DiscountCard create(DiscountCard discountCard);

    DiscountCard getByNumber(int number);

    DiscountCard getById(int id);

    boolean update(DiscountCard discountCard);

    boolean delete(int id);
}
