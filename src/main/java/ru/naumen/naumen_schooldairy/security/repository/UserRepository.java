package ru.naumen.naumen_schooldairy.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.naumen_schooldairy.security.entity.User;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью User
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Находит пользователя по его адресу электронной почты.
     *
     * @param email адрес электронной почты пользователя.
     * @return объект Optional, содержащий найденного пользователя, если он существует; иначе пустой Optional.
     */
    Optional<User> findByEmail(String email);
}
