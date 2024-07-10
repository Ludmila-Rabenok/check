package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.repository.CSV.PrintRepositoryCSV;
import main.java.ru.clevertec.check.service.PrintService;
import main.java.ru.clevertec.check.util.PreparingCheck;

public class PrintServiceImpl implements PrintService {
    private final PrintRepositoryCSV printRepositoryCSV;

    public PrintServiceImpl(PrintRepositoryCSV printRepositoryCSV) {
        this.printRepositoryCSV = printRepositoryCSV;
    }

    @Override
    public void printCheckToConsole(Check check) {
        for (String str : PreparingCheck.createLinesToPrintCheck(check)) {
            System.out.println(str);
        }
    }

    @Override
    public void exportCheckToCSV(Check check) {
        printRepositoryCSV.exportCheckToCSV(PreparingCheck.createLinesToPrintCheck(check));
    }

    @Override
    public void exportExceptionToCSV(String message) {
        printRepositoryCSV.exportExceptionToCSV(message);
    }
}