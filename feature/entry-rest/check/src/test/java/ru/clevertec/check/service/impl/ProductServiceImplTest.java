package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.DB.ProductRepository;
import ru.clevertec.check.service.ProductService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private ProductService productService;
    private static Product expectedProduct;

    @BeforeAll
    public static void init() {
        expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setDescription("Milk");
        expectedProduct.setPrice(20.5);
        expectedProduct.setQuantityInStock(50);
        expectedProduct.setWholesaleProduct(false);
    }

    @BeforeEach
    public void prepareTestData() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getById() {
        when(productRepository.readById(1)).thenReturn(expectedProduct);

        Product foundProduct = productService.getById(1);

        assertNotNull(foundProduct);
        assertSame(expectedProduct, foundProduct);
    }

    @Test
    void create() {
        when(productRepository.create(any(Product.class))).thenReturn(expectedProduct);

        Product actualProduct = productService.create(expectedProduct);

        assertNotNull(actualProduct);
        assertSame(expectedProduct, actualProduct);
    }

    @Test
    void delete() {
        when(productRepository.remove(any(Integer.class))).thenReturn(true);

        boolean b = productService.delete(1);

        assertTrue(b);
    }

    @Test
    void update() {
        when(productRepository.update(any(Product.class))).thenReturn(true);

        boolean b = productService.update(expectedProduct);

        assertTrue(b);
    }

    @Test
    void changeQuantityInStock() {
        when(productRepository.changeQuantityInStock(1, 50-2)).thenReturn(true);

        boolean b = productService.changeQuantityInStock(expectedProduct, 2);

        assertTrue(b);
    }

}
