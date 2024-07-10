package ru.clevertec.check.exception;

public class InternalServerException extends RuntimeException {

    public InternalServerException() {
        super("500");
    }
}
