package main.java.ru.clevertec.check.repository.CSV.impl;

import main.java.ru.clevertec.check.repository.CSV.PrintRepositoryCSV;
import main.java.ru.clevertec.check.util.CSVReaderWriter;

import java.util.ArrayList;
import java.util.List;

public class PrintRepositoryCSVImpl implements PrintRepositoryCSV {
    private static final String PATH = "\\src\\result.csv";
    private static final String ERROR = "ERROR";

    @Override
    public void exportCheckToCSV(List<String> list) {
        CSVReaderWriter.writeAll(list, PATH);
    }

    @Override
    public void exportExceptionToCSV(String message) {
        List<String> list = new ArrayList<>();
        list.add(ERROR);
        list.add(message);
        CSVReaderWriter.writeAll(list, PATH);
    }
}