package ru.clevertec.check.repository.DB;

import ru.clevertec.check.model.DiscountCard;

public interface DiscountCardRepository {

    DiscountCard create(DiscountCard discountCard);

    DiscountCard readByNumber(int number);

    DiscountCard readById(int id);

    boolean update(DiscountCard discountCard);

    boolean remove(int id);
}
