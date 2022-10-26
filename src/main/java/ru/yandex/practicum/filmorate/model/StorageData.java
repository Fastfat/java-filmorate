package ru.yandex.practicum.filmorate.model;

import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class StorageData {
    @NotNull(groups = {Update.class})
    Long id;
}
