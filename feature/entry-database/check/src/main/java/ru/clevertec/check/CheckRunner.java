package ru.clevertec.check;

import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.model.Check;
import ru.clevertec.check.repository.CSV.PrintRepositoryCSV;
import ru.clevertec.check.repository.CSV.impl.PrintRepositoryCSVImpl;
import ru.clevertec.check.repository.DB.DiscountCardRepository;
import ru.clevertec.check.repository.DB.DiscountCardRepositoryImpl;
import ru.clevertec.check.repository.DB.ProductRepository;
import ru.clevertec.check.repository.DB.ProductRepositoryImpl;
import ru.clevertec.check.service.*;
import ru.clevertec.check.service.impl.*;
import ru.clevertec.check.util.ArgumentDatasource;


public class CheckRunner {

    public static void main(String[] args) {
        DiscountCardRepository discountCardRepository = new DiscountCardRepositoryImpl();
        DiscountCardService discountCardService = new DiscountCardServiceImpl(discountCardRepository);
        OrderService orderService = new OrderServiceImpl(discountCardService);
        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl(productRepository);
        PositionService positionService = new PositionServiceImpl(productService);
        CheckService checkService = new CheckServiceImpl(orderService, positionService);
        PrintRepositoryCSV printRepositoryCSV = new PrintRepositoryCSVImpl();
        PrintService printService = new PrintServiceImpl(printRepositoryCSV);

        try {
            ArgumentDatasource.create(args);
            Check check = checkService.createCheck(args);
            printService.printCheckToConsole(check);
            printService.exportCheckToCSV(check);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException e) {
            printService.exportExceptionToCSV(e.getMessage());
            System.err.println(e.getMessage());
        }
    }
}
