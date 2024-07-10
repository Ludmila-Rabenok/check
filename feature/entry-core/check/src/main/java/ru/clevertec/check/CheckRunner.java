package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.repository.CSV.DiscountCardRepositoryCSV;
import main.java.ru.clevertec.check.repository.CSV.PrintRepositoryCSV;
import main.java.ru.clevertec.check.repository.CSV.ProductRepositoryCSV;
import main.java.ru.clevertec.check.repository.CSV.impl.DiscountCardRepositoryCSVImpl;
import main.java.ru.clevertec.check.repository.CSV.impl.PrintRepositoryCSVImpl;
import main.java.ru.clevertec.check.repository.CSV.impl.ProductRepositoryCSVImpl;
import main.java.ru.clevertec.check.service.*;
import main.java.ru.clevertec.check.service.impl.*;


public class CheckRunner {

    public static void main(String[] args) {
        DiscountCardRepositoryCSV discountCardRepositoryCSVImpl = new DiscountCardRepositoryCSVImpl();
        DiscountCardService discountCardService = new DiscountCardServiceImpl(discountCardRepositoryCSVImpl);
        OrderService orderService = new OrderServiceImpl(discountCardService);
        ProductRepositoryCSV productRepositoryCSV = new ProductRepositoryCSVImpl();
        ProductService productService = new ProductServiceImpl(productRepositoryCSV);
        PositionService positionService = new PositionServiceImpl(productService);
        CheckService checkService = new CheckServiceImpl(orderService, positionService);
        PrintRepositoryCSV printRepositoryCSV = new PrintRepositoryCSVImpl();
        PrintService printService = new PrintServiceImpl(printRepositoryCSV);

        try {
            Check check = checkService.createCheck(args);
            printService.printCheckToConsole(check);
            printService.exportCheckToCSV(check);
        } catch (BadRequestException | NotEnoughMoneyException | InternalServerException e) {
            printService.exportExceptionToCSV(e.getMessage());
            System.err.println(e.getMessage());
        } catch (Exception e) {
            try {
                throw new InternalServerException();
            } catch (InternalServerException exception) {
                printService.exportExceptionToCSV(exception.getMessage());
                System.err.println(exception.getMessage());
            }
        }
    }
}
