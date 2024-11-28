package ru.perminov.dto.user;

import lombok.Builder;
import lombok.Data;
import ru.perminov.model.Role;

import java.util.List;

@Data
@Builder
public class UserDtoIn {

    private Long id;

    private String email;

    private List<Role> roles;

}
