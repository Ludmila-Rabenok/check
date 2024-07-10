package ru.clevertec.check.repository.CSV.impl;


import ru.clevertec.check.repository.CSV.PrintRepositoryCSV;
import ru.clevertec.check.util.CSVReaderWriter;

import java.util.List;

public class PrintRepositoryCSVImpl implements PrintRepositoryCSV {
    private static final String PATH = "\\src\\result.csv";

    @Override
    public void exportCheckToCSV(List<String> list) {
        CSVReaderWriter.writeAll(list, PATH);
    }
}