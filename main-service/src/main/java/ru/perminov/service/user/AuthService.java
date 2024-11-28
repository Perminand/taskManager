package ru.perminov.service.user;

import ru.perminov.dto.user.UserDto;
import ru.perminov.dto.user.UserDtoIn;

public interface AuthService {
    UserDtoIn registered(UserDto userDto);
}
