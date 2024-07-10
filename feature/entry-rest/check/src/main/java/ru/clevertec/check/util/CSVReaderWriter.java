package ru.clevertec.check.util;

import ru.clevertec.check.exception.InternalServerException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReaderWriter {
    private static final String ABSOLUTE = "c:\\Users\\User\\IdeaProjects\\check";

    public static List<String> readAll(String path) {
        List<String> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(
                ABSOLUTE + path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String str = bufferedReader.readLine();
            while (str != null) {
                list.add(str);
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new InternalServerException();
        }
        return list;
    }

    public static void writeAll(List<String> list, String path) {
        try (FileWriter fw = new FileWriter(
                ABSOLUTE + path)) {
            for (String str : prepareForExport(list)) {
                fw.write(str);
                fw.flush();
            }
        } catch (IOException e) {
            throw new InternalServerException();
        }
    }

    private static List<String> prepareForExport(List<String> list) {
        return list.stream().map(s -> s + "\n").collect(Collectors.toList());
    }
}
