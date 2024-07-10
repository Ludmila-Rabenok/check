package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.repository.DB.DiscountCardRepository;
import ru.clevertec.check.service.DiscountCardService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountCardServiceImplTest {
    private final DiscountCardRepository discountCardRepository = mock(DiscountCardRepository.class);

    private DiscountCardService discountCardService;
    private static DiscountCard expectedCard;

    @BeforeAll
    public static void init() {
        expectedCard = new DiscountCard();
        expectedCard.setId(1);
        expectedCard.setNumber(1111);
        expectedCard.setDiscountAmount(3);
    }

    @BeforeEach
    public void prepareTestData() {
        discountCardService = new DiscountCardServiceImpl(discountCardRepository);
    }

    @Test
    void getByNumber() {
        when(discountCardRepository.readByNumber(1111)).thenReturn(expectedCard);

        DiscountCard foundCard = discountCardService.getByNumber(1111);

        assertNotNull(foundCard);
        assertSame(expectedCard, foundCard);
    }
}
