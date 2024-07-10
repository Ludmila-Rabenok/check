package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.DiscountCard;

public interface DiscountCardService {

    DiscountCard getByNumber(int number);
}
