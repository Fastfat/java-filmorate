package ru.yandex.practicum.filmorate.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(final String message) {
        super(message);
    }
}
