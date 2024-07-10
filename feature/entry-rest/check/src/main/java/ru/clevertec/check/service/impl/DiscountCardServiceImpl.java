package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.repository.DB.DiscountCardRepository;
import ru.clevertec.check.service.DiscountCardService;

public class DiscountCardServiceImpl implements DiscountCardService {
    private final DiscountCardRepository discountCardRepository;

    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    @Override
    public DiscountCard create(DiscountCard discountCard) {
        return discountCardRepository.create(discountCard);
    }

    @Override
    public DiscountCard getByNumber(int number) {
        return discountCardRepository.readByNumber(number);
    }

    @Override
    public DiscountCard getById(int id) {
        return discountCardRepository.readById(id);
    }

    @Override
    public boolean update(DiscountCard discountCard) {
        return discountCardRepository.update(discountCard);
    }

    @Override
    public boolean delete(int id) {
        return discountCardRepository.remove(id);
    }
}
