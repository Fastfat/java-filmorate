package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    HashMap<Integer, User> users = new HashMap<>();

    private int id = 1;
    private static final String INCORRECT_USER_EMAIL = "Неправильная почта пользователя";
    private static final String INCORRECT_USER_LOGIN = "Логин пользователя не должен содержать пробелы";
    private static final String INCORRECT_RELEASE_DATE_USER = "Неправильная дата релиза пользователя";
    private static final String EMPTY_LOGIN = "Логин пользователя не может быть пустым";
    private static final String UNKNOWN_USER = "Пользователя не существует";

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        log.info("Получен запрос к эндпоинту: POST /user, тело запроса: '{}'", user);
        checkCorrect(user);
        if (!StringUtils.hasText(user.getName())) {
            user.setName(user.getLogin());
        }
        user.setId(id);
        users.put(id, user);
        id++;
        return user;
    }

    @PutMapping("/users")
    public User setUser(@RequestBody User user) {
        log.info("Получен запрос к эндпоинту: PUT /user, тело запроса: '{}'", user);
        checkCorrect(user);
        if (!StringUtils.hasText(user.getName())) {
            user.setName(user.getLogin());
        }
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else {
            log.warn("Пользователь: '{}', не был обновлен, информация о пользователе: '{}'", user.getName(), user);
            throw new ValidationException(UNKNOWN_USER);
        }
        return user;
    }

    private void checkCorrect(User user) {
        if ((user.getEmail() == null || user.getEmail().isEmpty()) || !user.getEmail().contains("@")) {
            throw new ValidationException(INCORRECT_USER_EMAIL);
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException(EMPTY_LOGIN);
        }
        if (user.getLogin().contains(" ")) {
            throw new ValidationException(INCORRECT_USER_LOGIN);
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException(INCORRECT_RELEASE_DATE_USER);
        }
    }
}
