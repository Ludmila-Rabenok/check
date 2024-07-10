package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.model.Check;
import ru.clevertec.check.repository.CSV.PrintRepositoryCSV;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.util.PreparingCheck;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrintServiceTest {

    private final PrintRepositoryCSV printRepositoryCSV = mock(PrintRepositoryCSV.class);
    private final PreparingCheck preparingCheck = mock(PreparingCheck.class);

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private PrintService printService;
    private static List<String> expectedList;
    private static Check check;

    @BeforeAll
    public static void init() {
        expectedList = new ArrayList<>();
        expectedList.add("Привет");
        check = new Check();
    }

    @BeforeEach
    public void prepareTestData() {
        printService = new PrintServiceImpl(printRepositoryCSV, preparingCheck);
    }

    @Test
    void printCheckToConsole() {
        System.setOut(new PrintStream(outputStreamCaptor));
        when(preparingCheck.createLinesToPrintCheck(any(Check.class))).thenReturn(expectedList);

        printService.printCheckToConsole(check);

        assertEquals("Привет", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}
