package ru.yandex.practicum.filmorate.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Film {
    private int id;
    private String name;
    private String description;
    private LocalDateTime releaseDate;
    private int duration;

    public Film(int id, String name, String description, LocalDateTime releaseDate, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
