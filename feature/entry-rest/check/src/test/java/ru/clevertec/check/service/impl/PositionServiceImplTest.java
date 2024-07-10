package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.model.Position;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PositionServiceImplTest {

    private final ProductService productService = mock(ProductService.class);

    private PositionServiceImpl positionService;
    private static Product product;
    private static int quantity;
    private static Position expectedPosition;
    private static int percentDiscountCard;
    private static List<Position> list;

    @BeforeAll
    public static void init() {
        product = new Product();
        product.setId(1);
        product.setDescription("Milk");
        product.setPrice(20.5);
        product.setQuantityInStock(50);
        product.setWholesaleProduct(false);
        quantity = 2;
        double totalPrice = 41.0;
        double discount = 1.23;
        expectedPosition = new Position(product, quantity, totalPrice, discount);
        percentDiscountCard = 3;
        list = new ArrayList<>();
        list.add(expectedPosition);
    }

    @BeforeEach
    public void prepareTestData() {
        positionService = new PositionServiceImpl(productService);
    }

    @Test
    void create() {
        when(productService.getById(1)).thenReturn(product);

        Position actualPosition = positionService.create(1, quantity, percentDiscountCard);

        assertNotNull(actualPosition);
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    void changeQuantityOfProductsInStock() {
        when(productService.changeQuantityInStock(product, quantity)).thenReturn(true);

        int count = positionService.changeQuantityOfProductsInStock(list);

        assertEquals(1,count);
    }
}