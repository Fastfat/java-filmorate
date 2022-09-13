package ru.yandex.practicum.filmorate.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String login;
    private String name;
    private LocalDateTime birthday;


}
