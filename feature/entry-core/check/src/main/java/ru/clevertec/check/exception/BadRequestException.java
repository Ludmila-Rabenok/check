package main.java.ru.clevertec.check.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("BAD REQUEST");
    }
}
