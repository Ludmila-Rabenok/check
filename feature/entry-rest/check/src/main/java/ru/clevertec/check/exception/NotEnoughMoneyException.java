package ru.clevertec.check.exception;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
        super("400");
    }
}
