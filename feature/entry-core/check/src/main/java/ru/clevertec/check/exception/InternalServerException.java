package main.java.ru.clevertec.check.exception;

public class InternalServerException extends RuntimeException {

    public InternalServerException() {
        super("INTERNAL SERVER ERROR");
    }
}
