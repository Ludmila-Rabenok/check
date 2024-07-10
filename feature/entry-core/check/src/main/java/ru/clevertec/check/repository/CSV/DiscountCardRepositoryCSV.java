package main.java.ru.clevertec.check.repository.CSV;

import main.java.ru.clevertec.check.model.DiscountCard;

public interface DiscountCardRepositoryCSV {

    DiscountCard getByNumber(int number);
}
