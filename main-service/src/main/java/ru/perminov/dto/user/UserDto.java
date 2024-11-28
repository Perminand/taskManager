package ru.perminov.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {

    @NotBlank(message = "email не должен быть пустым")
    @Email(message = "Введите корректный email")
    private String email;

    @NotBlank(message = "password не может быть пустым")
    private String password;
}
