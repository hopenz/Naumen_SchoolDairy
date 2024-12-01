package ru.naumen.naumen_schooldairy.security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.entity.Teacher;

import java.util.Collection;
import java.util.List;

/**
 * Класс, представляющий пользователя в системе
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    /**
     * Уникальный идентификатор пользователя.
     * Используется для однозначной идентификации пользователя в системе.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Адрес электронной почты пользователя.
     * Должен быть уникальным и не может быть пустым.
     */
    @Column(unique = true, nullable = false)
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email can't be blank")
    private String email;

    /**
     * Пароль пользователя.
     * Не может быть пустым.
     */
    @NotBlank(message = "Password can't be blank")
    @Column(nullable = false)
    private String Password;

    /**
     * Статус верификации пользователя.
     * True, если пользователь успешно верифицирован; иначе false.
     */
    private Boolean isVerified;

    /**
     * Роль пользователя в системе.
     * Определяет права доступа и функциональность, доступные пользователю.
     */
    @Column(name = "role", columnDefinition = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Связь с сущностью Student.
     * Один пользователь может быть связан с одной сущностью Student.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;

    /**
     * Связь с сущностью Teacher.
     * Один пользователь может быть связан с одной сущностью Teacher.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Teacher teacher;

    /**
     * Возвращает коллекцию прав доступа пользователя на основе его роли.
     *
     * @return коллекция прав доступа (GrantedAuthority).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Возвращает имя пользователя, используемое для аутентификации.
     *
     * @return адрес электронной почты пользователя.
     */
    @Override
    public String getUsername() {
        return this.email;
    }
}
