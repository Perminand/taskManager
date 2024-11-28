package ru.perminov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perminov.dto.user.UserDto;
import ru.perminov.dto.user.UserDtoIn;
import ru.perminov.service.user.AuthService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthMapper authMapper;

    @PostMapping("/registered")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoIn registerUser(@RequestBody UserDto userDto) {
        log.info("POST запрос на регистрацию {}", userDto.getEmail());
        return authService.registered(userDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoIn login(@RequestBody UserDto userDto) {
        log.info("POST запрос на вход {}", userDto.getEmail());
        return authMapper.toTokenDto(authService.signUp(userMapper.toUser(user)));
    }
}
