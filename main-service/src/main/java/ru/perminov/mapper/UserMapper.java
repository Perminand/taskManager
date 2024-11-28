package ru.perminov.mapper;

import lombok.RequiredArgsConstructor;
import ru.perminov.dto.user.UserDto;
import ru.perminov.dto.user.UserDtoIn;
import ru.perminov.model.User;

@RequiredArgsConstructor
public class UserMapper {


    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(user.getEmail());
        return user;
    }

    public static UserDtoIn toDto(User user) {
        return UserDtoIn.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
