package main.java.ru.clevertec.check.repository.CSV.impl;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.repository.CSV.DiscountCardRepositoryCSV;
import main.java.ru.clevertec.check.util.CSVReaderWriter;

import java.util.List;

public class DiscountCardRepositoryCSVImpl implements DiscountCardRepositoryCSV {
    private static final String PATH = "\\src\\main\\resources\\discountCards.csv";
    private static final String DELIMITER = ";";
    private static final int INDEX_ID = 0;
    private static final int INDEX_NUMBER = 1;
    private static final int INDEX_DISCOUNT_AMOUNT = 2;

    @Override
    public DiscountCard getByNumber(int number) {
        List<String> list = CSVReaderWriter.readAll(PATH);
        DiscountCard discountCard = null;
        for (int i = 1; i < list.size() - 1; i++) {
            String[] array = list.get(i).split(DELIMITER);
            int numberFromArray = Integer.parseInt(array[INDEX_NUMBER]);
            if (numberFromArray == number) {
                discountCard = new DiscountCard();
                discountCard.setId(Integer.parseInt(array[INDEX_ID]));
                discountCard.setNumber(numberFromArray);
                discountCard.setDiscountAmount(Integer.parseInt(array[INDEX_DISCOUNT_AMOUNT]));
                break;
            }
        }
        if (discountCard == null) {
            throw new BadRequestException();
        }
        return discountCard;
    }
}
