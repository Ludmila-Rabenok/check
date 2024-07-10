package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.repository.CSV.DiscountCardRepositoryCSV;
import main.java.ru.clevertec.check.service.DiscountCardService;

public class DiscountCardServiceImpl implements DiscountCardService {
    private final DiscountCardRepositoryCSV discountCardRepositoryCSV;

    public DiscountCardServiceImpl(DiscountCardRepositoryCSV discountCardRepositoryCSV) {
        this.discountCardRepositoryCSV = discountCardRepositoryCSV;
    }

    @Override
    public DiscountCard getByNumber(int number) {
        return discountCardRepositoryCSV.getByNumber(number);
    }
}
