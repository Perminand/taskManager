package ru.perminov.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.dto.user.UserDto;
import ru.perminov.dto.user.UserDtoIn;
import ru.perminov.exceptions.errors.ConflictException;
import ru.perminov.mapper.UserMapper;
import ru.perminov.model.Role;
import ru.perminov.model.User;
import ru.perminov.repository.RoleRepository;
import ru.perminov.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDtoIn registered(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException("email уже зарегистрирован");
        }
        Role role = roleRepository.findRoleByName("USER");
        user.setRoles(new ArrayList<>(List.of(role)));
        userRepository.save(user);
        return UserMapper.toDto(user);
    }
}
