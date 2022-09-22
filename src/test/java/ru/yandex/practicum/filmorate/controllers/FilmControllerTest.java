package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    FilmController filmController = new FilmController();

    @Test
    void addFilmWithEmptyList() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> filmController.addFilm(new Film(1, null, "Shark",
                        LocalDateTime.now(), 23))
        );
    }

    @Test
    void addFilmWithWrongDataRelease() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> filmController.addFilm(new Film(1, "Shark2D",
                        "Shark", LocalDate.of(1890, 11, 28).atStartOfDay(), 23))
        );
    }

    @Test
    void addFilmWithWrongDuration() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> filmController.addFilm(new Film(1, "Shark2D",
                        "Shark", LocalDateTime.now(), -23))
        );
    }
}