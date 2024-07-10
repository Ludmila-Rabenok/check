package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.Check;

public interface PrintService {

    void exportExceptionToCSV(String message);

    void printCheckToConsole(Check check);

    void exportCheckToCSV(Check check);

}
