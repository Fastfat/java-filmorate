package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends StorageData {

    private int id;
    @Email
    private String email;
    @NotBlank
    private String login;
    private String name;
    private LocalDate birthday;
    @JsonIgnore
    private transient Set<Long> friendIds = new HashSet<>();
}

