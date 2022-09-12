package ru.yandex.practicum.filmorate.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Film {
    private int id = 4;
    private String name = "aa";
    private String description = "xx";
    private LocalDateTime releaseDate = LocalDateTime.now();
    private int duration = 2;


}
