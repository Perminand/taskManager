package ru.perminov.exceptions.errors;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String massage) {
        super(massage);
    }
}