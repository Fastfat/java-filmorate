package ru.yandex.practicum.filmorate.exceptions;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ValidationException extends RuntimeException {
    public ValidationException(final String message) {
        super(message);
    }
}
