package ru.naumen.naumen_schooldairy.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.security.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
