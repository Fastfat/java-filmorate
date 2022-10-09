package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class FilmController {

    private HashMap<Integer, Film> films = new HashMap<>();
    private int id = 1;
    private static final String INCORRECT_RELEASE_DATE = "Неправильная дата релиза фильма";
    private static final String INCORRECT_DURATION = "Неправильная продолжительность фильма";
    private static final String EMPTY_NAME = "Имя фильма не может быть пустым";
    private static final String UNKNOWN_FILM = "Фильма не существует";
    private static final String LONG_DESCRIPTION = "Длина описания не должна привышать 200 символов";

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        log.info("Получен запрос к эндпоинту: POST /film, тело запроса: '{}'", film);
        checkCorrect(film);
        film.setId(id);
        films.put(id, film);
        id++;
        log.info("Фильм: '{}', был успешно добавлен с идентификатором '{}'", film.getName(), film.getId());
        return film;
    }

    @PutMapping("/films")
    public Film setFilm(@RequestBody Film film) {
        log.info("Получен запрос к эндпоинту: PUT /film, тело запроса: '{}'", film);
        checkCorrect(film);
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
        } else {
            log.warn("Фильм: '{}', не был обновлен, информация о фильме: '{}'", film.getName(), film);
            throw new ValidationException(UNKNOWN_FILM);
        }
        return film;
    }

    private void checkCorrect(Film film) {
        int limit = 200;
        LocalDate dateRelease = LocalDate.of(1895, 11, 28);
        if (film.getDescription().length() > limit) {
            throw new ValidationException(LONG_DESCRIPTION);
        }
        if (film.getName() == null || film.getName().isEmpty()) {
            throw new ValidationException(EMPTY_NAME);
        }
        if (film.getReleaseDate().isBefore(ChronoLocalDate.from(dateRelease.atStartOfDay()))) {
            throw new ValidationException(INCORRECT_RELEASE_DATE);
        }
        if (film.getDuration() < 0) {
            throw new ValidationException(INCORRECT_DURATION);
        }
    }
}
