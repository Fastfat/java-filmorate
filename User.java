package ru.yandex.practicum.filmorate.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {
    private int id = 3;
    private String email = "ff";
    private String login = "fd";
    private String name = "sd";
    private LocalDateTime birthday = LocalDateTime.now();


}
