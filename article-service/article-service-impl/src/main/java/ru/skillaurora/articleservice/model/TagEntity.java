package ru.skillaurora.articleservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tags")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Название не должно быть пустым.")
    @Size(max = 16, message = "Название не должно превышать 16 символов.")
    private String name;
    @Size(max = 128, message = "Описание не может превышать 128 символов.")
    private String description;
    @NotNull(message = "Не указано количество использований.")
    private Long countUsage;
}
