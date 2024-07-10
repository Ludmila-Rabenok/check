package main.java.ru.clevertec.check.repository.CSV.impl;

import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.model.Position;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.repository.CSV.ProductRepositoryCSV;
import main.java.ru.clevertec.check.util.CSVReaderWriter;

import java.util.List;

public class ProductRepositoryCSVImpl implements ProductRepositoryCSV {
    private static final String PATH = "\\src\\main\\resources\\products.csv";
    private static final String DELIMITER = ";";
    private static final int INDEX_ID = 0;
    private static final int INDEX_DESCRIPTION = 1;
    private static final int INDEX_PRICE = 2;
    private static final int INDEX_QUANTITY_IN_STOCK = 3;
    private static final int INDEX_WHOLESALE_PRODUCT = 4;

    @Override
    public Product getById(int id) {
        List<String> list = CSVReaderWriter.readAll(PATH);
        Product product = null;
        for (int i = 1; i < list.size() - 1; i++) {
            String[] array = list.get(i).split(DELIMITER);
            int idFromArray = Integer.parseInt(array[INDEX_ID]);
            if (idFromArray == id) {
                product = new Product();
                product.setId(idFromArray);
                product.setDescription(array[INDEX_DESCRIPTION]);
                product.setPrice(Double.parseDouble(array[INDEX_PRICE]));
                product.setQuantityInStock(Long.parseLong(array[INDEX_QUANTITY_IN_STOCK]));
                product.setWholesaleProduct(Boolean.parseBoolean(array[INDEX_WHOLESALE_PRODUCT]));
                break;
            }
        }
        if (product == null) {
            throw new BadRequestException();
        }
        return product;
    }

    @Override
    public void changeQuantityInStock(List<Position> positions) {
        List<String> list = CSVReaderWriter.readAll(PATH);
        for (int i = 1; i < list.size() - 1; i++) {
            String[] array = list.get(i).split(DELIMITER);
            int idFromCSV = Integer.parseInt(array[INDEX_ID]);
            Position positionOrNull = equalsProductIdFromCSVAndPositions(idFromCSV, positions);
            if (!(positionOrNull == null)) {
                array[INDEX_QUANTITY_IN_STOCK] =
                        String.valueOf(Integer.parseInt(array[INDEX_QUANTITY_IN_STOCK]) - positionOrNull.getQuantity());
                String str = String.join(DELIMITER, array);
                list.set(i, str);
            }
        }
        CSVReaderWriter.writeAll(list, PATH);
    }

    private Position equalsProductIdFromCSVAndPositions(int idFromCSV, List<Position> positions) {
        for (Position position : positions) {
            if (position.getProduct().getId() == idFromCSV) {
                return position;
            }
        }
        return null;
    }
}