package ru.clevertec.check.service.impl;

import ru.clevertec.check.model.Check;
import ru.clevertec.check.repository.CSV.PrintRepositoryCSV;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.util.PreparingCheck;

public class PrintServiceImpl implements PrintService {
    private final PrintRepositoryCSV printRepositoryCSV;
    private final PreparingCheck preparingCheck;

    public PrintServiceImpl(PrintRepositoryCSV printRepositoryCSV, PreparingCheck preparingCheck) {
        this.printRepositoryCSV = printRepositoryCSV;
        this.preparingCheck = preparingCheck;
    }

    @Override
    public void printCheckToConsole(Check check) {
        for (String str : preparingCheck.createLinesToPrintCheck(check)) {
            System.out.println(str);
        }
    }

    @Override
    public void exportCheckToCSV(Check check) {
        printRepositoryCSV.exportCheckToCSV(preparingCheck.createLinesToPrintCheck(check));
    }
}