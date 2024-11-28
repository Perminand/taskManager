package ru.perminov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
