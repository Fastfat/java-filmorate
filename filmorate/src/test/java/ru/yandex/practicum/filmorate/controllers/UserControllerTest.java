package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    UserController userController = new UserController();

    @Test
    void addUserWithEmptyEmail() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User(1, null, "Shark",
                        "qwe1", LocalDateTime.now()))
        );
    }

    @Test
    void addUserWithWrongEmail() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User(1, "null", "Shark",
                        "qwe1", LocalDateTime.now()))
        );
    }

    @Test
    void addUserWithEmptyLogin() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User(1, "null@", "",
                        "qwe1", LocalDateTime.now()))
        );
    }

    @Test
    void addUserLoginWithSpaces() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User(1, "nul@l", "Sha rk",
                        "qwe1", LocalDateTime.now()))
        );
    }

    @Test
    void addUserWithWrongBirthday() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User(1, "null@", "Shark",
                        "qwe1", LocalDateTime.now().plusHours(1)))
        );
    }
}