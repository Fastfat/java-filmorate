package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    FilmController filmController = new FilmController(new FilmService(new InMemoryFilmStorage(), new InMemoryUserStorage()));

    @Test
    void addFilmWithEmptyList() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> filmController.addFilm(new Film(null, "Shark",
                        LocalDate.now(), 23, new HashSet<>(), 4))
        );
    }

    @Test
    void addFilmWithWrongDataRelease() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> filmController.addFilm(new Film("Shark2D",
                        "Shark", LocalDate.of(1890, 11, 28),
                        23, new HashSet<>(), 4))
        );
    }

    @Test
    void addFilmWithWrongDuration() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> filmController.addFilm(new Film("Shark2D",
                        "Shark", LocalDate.now(), -23, new HashSet<>(), 4))
        );
    }

    @Test
    void addLikeWithEmptyList() {
        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class, () -> filmController.addLike(1, 2)
        );
    }

    @Test
    void removeLikeWithEmptyList() {
        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class, () -> filmController.removeLike(1, 2)
        );
    }

    @Test
    void getPopularWithEmptyList() {
        assertTrue(filmController.getPopular(2).isEmpty());
    }
}
