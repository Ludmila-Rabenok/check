package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.Check;
import main.java.ru.clevertec.check.model.Order;

public interface CheckService {
    Check createCheck(String[] args);
}
