package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Update;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        final List<User> users = service.getAll();
        log.info("Get all users {}", users.size());
        return users;
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody final User user) {
        log.info("Получен запрос к эндпоинту: POST /user, тело запроса: '{}'", user);
        return service.create(user);
    }

    @PutMapping("/users")
    public User setUser(@Validated(Update.class) @RequestBody User user) {
        log.info("Получен запрос к эндпоинту: PUT /user, тело запроса: '{}'", user);
        return service.update(user);
    }

    @GetMapping("/users/{id}")
    public User get(@PathVariable long id) {
        log.info("Get user id={}", id);
        return service.get(id);
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable long id, @PathVariable long friendId) {
        log.info("Add friend id={} to user={}", friendId, id);
        service.addFriend(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable long id, @PathVariable long friendId) {
        log.info("Remove friend id={} from user={}", friendId, id);
        service.removeFriend(id, friendId);
    }

    @GetMapping("/users/{id}/friends")
    public List<User> getFriends(@PathVariable int id) {
        log.info("Get friends from user={}", id);
        return service.getFriends(id);
    }

    @GetMapping("users/{id}/friends/common/{otherId}")
    public List<User> getOurFriends(@PathVariable long id, @PathVariable long otherId) {
        log.info("Get our friends from user={} and other user={}", id, otherId);
        return service.getOurFriends(id, otherId);
    }
}
