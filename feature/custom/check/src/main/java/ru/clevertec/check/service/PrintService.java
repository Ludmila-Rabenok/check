package ru.clevertec.check.service;

import ru.clevertec.check.model.Check;

public interface PrintService {

    void printCheckToConsole(Check check);

    void exportCheckToCSV(Check check);

}
