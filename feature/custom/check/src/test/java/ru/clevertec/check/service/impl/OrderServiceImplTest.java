package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.model.OrderBuilder;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.OrderService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    private final DiscountCardService discountCardService = mock(DiscountCardService.class);

    private OrderService orderService;
    private static Order expectedOrder;
    private static Map<Integer, Integer> mapWithProductIdAndQuantity;
    private static int numberCard;
    private static double balanceDebitCard;
    private static DiscountCard discountCard;

    @BeforeAll
    public static void init() {
        mapWithProductIdAndQuantity = new HashMap<>();
        mapWithProductIdAndQuantity.put(1, 1);
        mapWithProductIdAndQuantity.put(2, 2);
        numberCard = 1111;
        balanceDebitCard = 200.5;
        discountCard = mock(DiscountCard.class);
        expectedOrder = new OrderBuilder()
                .setProductIdAndQuantityMap(mapWithProductIdAndQuantity)
                .setDiscountCard(discountCard)
                .setBalanceDebitCard(200.5)
                .build();
    }

    @BeforeEach
    public void prepareTestData() {
        orderService = new OrderServiceImpl(discountCardService);
    }

    @Test
    void createOrder() {
        when(discountCardService.getByNumber(1111)).thenReturn(discountCard);

        Order foundOrder = orderService.createOrder(mapWithProductIdAndQuantity, numberCard, balanceDebitCard);

        assertNotNull(foundOrder);
        assertEquals(foundOrder, expectedOrder);
    }
}
