package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    UserController userController = new UserController(new UserService(new InMemoryUserStorage()));

    @Test
    void addUserWithEmptyEmail() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User(null, "Shark",
                        "qwe1", LocalDate.now(), new HashSet<>()))
        );
    }

    @Test
    void addUserWithWrongEmail() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User("null", "Shark",
                        "qwe1", LocalDate.now(), new HashSet<>()))
        );
    }

    @Test
    void addUserWithEmptyLogin() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User("null@", "",
                        "qwe1", LocalDate.now(), new HashSet<>()))
        );
    }

    @Test
    void addUserLoginWithSpaces() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User("nul@l", "Sha rk",
                        "qwe1", LocalDate.now(), new HashSet<>()))
        );
    }

    @Test
    void addUserWithWrongBirthday() {
        ValidationException ex = assertThrows(
                ValidationException.class, () -> userController.addUser(new User("null@", "Shark",
                        "qwe1", LocalDate.now().plusDays(1), new HashSet<>()))
        );
    }

    @Test
    void addFriendWithEmptyList() {
        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class, () -> userController.addFriend(1, 2)
        );
    }

    @Test
    void removeFriendWithEmptyList() {
        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class, () -> userController.removeFriend(1, 2)
        );
    }

    @Test
    void getFriendsWithEmptyList() {
        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class, () -> userController.getFriends(1)
        );
    }

    @Test
    void getOurFriendsWithEmptyList() {
        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class, () -> userController.getOurFriends(1, 2)
        );
    }
}