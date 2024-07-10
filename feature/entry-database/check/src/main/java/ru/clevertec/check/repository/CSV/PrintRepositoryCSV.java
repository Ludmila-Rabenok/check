package ru.clevertec.check.repository.CSV;

import java.util.List;

public interface PrintRepositoryCSV {

    void exportCheckToCSV(List<String> list);

    void exportExceptionToCSV(String message);
}
