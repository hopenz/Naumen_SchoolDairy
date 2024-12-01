package ru.naumen.naumen_schooldairy.security.service.implementation;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.entity.Student;
import ru.naumen.naumen_schooldairy.data.entity.Teacher;
import ru.naumen.naumen_schooldairy.exception.ResourceNotFoundException;
import ru.naumen.naumen_schooldairy.security.dto.requests.*;
import ru.naumen.naumen_schooldairy.security.dto.responses.GeneralAPIResponse;
import ru.naumen.naumen_schooldairy.security.dto.responses.RegisterResponse;
import ru.naumen.naumen_schooldairy.security.dto.responses.RegisterVerifyResponse;
import ru.naumen.naumen_schooldairy.security.dto.responses.UserProfile;
import ru.naumen.naumen_schooldairy.security.entity.Role;
import ru.naumen.naumen_schooldairy.security.entity.User;
import ru.naumen.naumen_schooldairy.security.repository.UserRepository;
import ru.naumen.naumen_schooldairy.security.service.AuthenticationService;
import ru.naumen.naumen_schooldairy.security.service.EmailService;
import ru.naumen.naumen_schooldairy.security.service.JwtService;
import ru.naumen.naumen_schooldairy.security.service.OtpService;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сервиса аутентификации
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImplementation implements AuthenticationService {

    /**
     * Репозиторий для работы с пользователями
     */
    private final UserRepository userRepository;

    /**
     * Кодировщик паролей
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Сервис для работы с OTP
     */
    private final OtpService otpService;

    /**
     * Сервис для отправки электронных писем
     */
    private final EmailService emailService;

    /**
     * Сервис для работы с JWT
     */
    private final JwtService jwtService;

    /**
     * Менеджер кэша
     */
    private final CacheManager cacheManager;

    /**
     * Менеджер аутентификации
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрирует нового пользователя или обновляет существующего.
     *
     * @param registerRequest запрос на регистрацию пользователя, содержащий необходимые данные.
     * @return ResponseEntity с сообщением о результате регистрации.
     */
    @Override
    public ResponseEntity<RegisterResponse> registerUser(RegisterRequest registerRequest) {
        try {
            log.info("Received request to register user with email {}", registerRequest.getEmail());
            Optional<User> existingUserOpt = userRepository.findByEmail(registerRequest.getEmail().trim().toLowerCase());
            if (existingUserOpt.isPresent()) {
                User existingUser = existingUserOpt.get();
                log.info("User already exists with email {}", registerRequest.getEmail());
                if (existingUser.getIsVerified()) {
                    return new ResponseEntity<>(RegisterResponse.builder()
                            .message("User already exists")
                            .build(), HttpStatus.BAD_REQUEST);
                } else {
                    log.info("User already exists but not verified with email {}, so their details will be updated", registerRequest.getEmail());
                    updateUserDetails(existingUser, registerRequest);
                    String otpToBeMailed = otpService.getOtpForEmail(registerRequest.getEmail());
                    CompletableFuture<Integer> emailResponse = emailService.sendEmailWithRetry(registerRequest.getEmail(), otpToBeMailed);
                    if (emailResponse.get() == -1) {
                        return new ResponseEntity<>(RegisterResponse.builder()
                                .message("Failed to send OTP email. Please try again later.")
                                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    userRepository.save(existingUser);
                    return new ResponseEntity<>(RegisterResponse.builder()
                            .message("An email with OTP has been sent to your email address. Kindly verify.")
                            .build(), HttpStatus.CREATED);
                }
            } else {
                log.info("User does not exist with email {}, so this user will be created", registerRequest.getEmail());
                User newUser = createUser(registerRequest);
                String otpToBeMailed = otpService.getOtpForEmail(registerRequest.getEmail());
                CompletableFuture<Integer> emailResponse = emailService.sendEmailWithRetry(registerRequest.getEmail(), otpToBeMailed);
                if (emailResponse.get() == -1) {
                    return new ResponseEntity<>(RegisterResponse.builder()
                            .message("Failed to send OTP email. Please try again later.")
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
                userRepository.save(newUser);
                log.info("User saved with the email {}", registerRequest.getEmail());
                return new ResponseEntity<>(RegisterResponse.builder()
                        .message("An email with OTP has been sent to your email address. Kindly verify.")
                        .build(), HttpStatus.CREATED);
            }
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send OTP email for user with email {}", registerRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RegisterResponse.builder()
                    .message("Failed to send OTP email. Please try again later.")
                    .build());
        } catch (DataIntegrityViolationException ex) {
            log.info("User already exists with phone number {}", registerRequest.getPhoneNumber());
            return new ResponseEntity<>(RegisterResponse.builder()
                    .message("User already exists with this phone number. Please try again with a different phone number.")
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Failed to register user with email {}", registerRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RegisterResponse.builder()
                    .message("Failed to register user. Please try again later.")
                    .build());
        }
    }

    /**
     * Обновляет данные существующего пользователя на основе запроса регистрации.
     *
     * @param user            существующий пользователь, чьи данные необходимо обновить.
     * @param registerRequest запрос на регистрацию с новыми данными пользователя.
     */
    private void updateUserDetails(User user, RegisterRequest registerRequest) {
        requestToUser(registerRequest, user);
        if (registerRequest.getRole() == Role.ROLE_STUDENT) {
            Student student = new Student();
            student.setName(registerRequest.getFirstName());
            student.setSurname(registerRequest.getLastName());
            student.setPhoneNumber(registerRequest.getPhoneNumber());
            student.setUser(user);
            user.setStudent(student);
        } else {
            Teacher teacher = new Teacher();
            teacher.setName(registerRequest.getFirstName());
            teacher.setSurname(registerRequest.getLastName());
            teacher.setPhoneNumber(registerRequest.getPhoneNumber());
            teacher.setUser(user);
            user.setTeacher(teacher);
        }
    }

    /**
     * Создает нового пользователя на основе данных из запроса регистрации.
     *
     * @param registerRequest запрос на регистрацию, содержащий необходимые данные.
     * @return созданный объект User.
     */
    private User createUser(RegisterRequest registerRequest) {
        User user = new User();
        Student student;
        Teacher teacher;
        if (registerRequest.getRole() == Role.ROLE_STUDENT) {
            student = new Student();
            student.setName(registerRequest.getFirstName());
            student.setSurname(registerRequest.getLastName());
            student.setPhoneNumber(registerRequest.getPhoneNumber());
            user.setStudent(student);
            student.setUser(user);
        } else {
            teacher = new Teacher();
            teacher.setName(registerRequest.getFirstName());
            teacher.setSurname(registerRequest.getLastName());
            teacher.setPhoneNumber(registerRequest.getPhoneNumber());
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setClassName("ClassName");
            schoolClass.setTeacher(teacher);
            teacher.setSchoolClasses(new HashSet<>(Set.of(schoolClass)));
            user.setTeacher(teacher);
            teacher.setUser(user);
        }
        requestToUser(registerRequest, user);
        return user;
    }

    /**
     * Обновляет данные пользователя на основе запроса регистрации.
     *
     * @param registerRequest запрос на регистрацию с новыми данными пользователя.
     * @param user            существующий пользователь, чьи данные необходимо обновить.
     */
    private void requestToUser(RegisterRequest registerRequest, User user) {
        user.setEmail(registerRequest.getEmail().trim().toLowerCase());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        user.setIsVerified(false);
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Верифицирует регистрацию пользователя на основе введенного OTP (одноразового пароля).
     *
     * @param registerVerifyRequest запрос на верификацию, содержащий адрес электронной почты и OTP.
     * @return ResponseEntity с результатом верификации.
     */
    @Override
    public ResponseEntity<?> verifyUserRegistration(RegisterVerifyRequest registerVerifyRequest) {
        String emailEntered = registerVerifyRequest.getEmail().trim().toLowerCase();
        String otpEntered = registerVerifyRequest.getOtp().trim();
        try {
            User user = userRepository.findByEmail(emailEntered).orElseThrow(
                    ResourceNotFoundException::new
            );
            String cachedOtp = cacheManager.getCache("user").get(emailEntered, String.class);
            if (cachedOtp == null) {
                log.info("the otp is not present in cache memory, it has expired for user {}, kindly retry and Register", emailEntered);
                return new ResponseEntity<>(GeneralAPIResponse.builder().message("Otp has been expired for user " + emailEntered).build(), HttpStatus.REQUEST_TIMEOUT);
            } else if (!otpEntered.equals(cachedOtp)) {
                log.info("the entered otp does not match the otp Stored in cache for email {}", emailEntered);
                return new ResponseEntity<>(GeneralAPIResponse.builder().message("Incorrect otp has been entered").build(), HttpStatus.BAD_REQUEST);
            } else {
                user.setIsVerified(true);
                userRepository.save(user);
                log.info("the user email {} is successfully verified", user.isEnabled());
                RegisterVerifyResponse jwtToken = jwtService.generateJwtToken(user);
                return new ResponseEntity<>(jwtToken, HttpStatus.CREATED);

            }
        } catch (ResourceNotFoundException ex) {
            log.info("user with email {} not found in database", emailEntered);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("user with this email does not exist").build(), HttpStatus.NOT_FOUND);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Аутентифицирует пользователя на основе введенных учетных данных.
     *
     * @param loginRequest запрос на вход, содержащий адрес электронной почты и пароль.
     * @return ResponseEntity с результатом аутентификации, включая JWT-токен при успешной аутентификации.
     */
    @Override
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail().trim().toLowerCase();
        String password = loginRequest.getPassword();
        try {
            User user = userRepository.findByEmail(email).orElseThrow(
                    ResourceNotFoundException::new
            );
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            if (!user.getIsVerified()) {
                return new ResponseEntity<>(GeneralAPIResponse.builder().message("User is not verified").build(), HttpStatus.BAD_REQUEST);
            }

            RegisterVerifyResponse jwtToken = jwtService.generateJwtToken(user);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);

        } catch (ResourceNotFoundException ex) {
            log.info("user whose email is {} not found in Database", email);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("User with this email does not exist").build(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to authenticate user with email {}", email, e);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("Invalid credentials").build(), HttpStatus.BAD_REQUEST);
        }


    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Повторно отправляет OTP на адрес электронной почты пользователя для восстановления доступа.
     *
     * @param forgotPasswordRequest запрос на восстановление пароля, содержащий адрес электронной почты.
     * @return ResponseEntity с результатом операции отправки OTP.
     */
    @Override
    public ResponseEntity<?> resendOtp(ForgotPasswordRequest forgotPasswordRequest) {
        String email = forgotPasswordRequest.getEmail().trim().toLowerCase();
        try {
            User user = userRepository.findByEmail(email).orElseThrow(
                    ResourceNotFoundException::new
            );
            if (cacheManager.getCache("user").get(email, String.class) != null) {
                log.info("the otp is already present in cache memory for user {}, kindly retry after some time", email);
                return new ResponseEntity<>(GeneralAPIResponse.builder().message("Kindly retry after 1 minute").build(), HttpStatus.TOO_MANY_REQUESTS);
            }
            String otpToBeSend = otpService.getOtpForEmail(email);
            CompletableFuture<Integer> emailResponse = emailService.sendEmailWithRetry(email, otpToBeSend);
            if (emailResponse.get() == -1) {
                return new ResponseEntity<>(GeneralAPIResponse.builder().message("Failed to send OTP email. Please try again later.").build(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("An email with OTP has been sent to your email address. Kindly verify.").build(), HttpStatus.OK);

        } catch (UnsupportedEncodingException e) {
            log.error("Failed to send OTP email for user with email {}", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RegisterResponse.builder()
                    .message("Failed to send OTP email. Please try again later.")
                    .build());
        } catch (ResourceNotFoundException ex) {
            log.info("user with email {} not found in Database", email);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("User with email not found in database").build(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to resend OTP for user with email {}", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RegisterResponse.builder()
                    .message("Failed to resend OTP. Please try again later.")
                    .build());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Верифицирует одноразовый пароль (OTP) для пользователя.
     *
     * @param registerVerifyRequest запрос на верификацию, содержащий адрес электронной почты и OTP.
     * @return ResponseEntity с результатом верификации.
     */
    @Override
    public ResponseEntity<?> verifyOtp(RegisterVerifyRequest registerVerifyRequest) {
        String email = registerVerifyRequest.getEmail().trim().toLowerCase();
        String otp = registerVerifyRequest.getOtp().trim();
        try {
            User user = userRepository.findByEmail(email).orElseThrow(
                    ResourceNotFoundException::new
            );
        } catch (ResourceNotFoundException ex) {
            log.info("user with email {} not found in database ", email);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("iUser with this email does not exist").build(), HttpStatus.NOT_FOUND);
        }
        String cachedOtp = cacheManager.getCache("user").get(email, String.class);
        if (cachedOtp == null) {
            log.info("the otp is not present in cache memory, it has expired for user {}, kindly retry", email);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("Otp has been expired for user " + email).build(), HttpStatus.REQUEST_TIMEOUT);
        } else if (!otp.equals(cachedOtp)) {
            log.info("entered otp does not match the otp Stored in cache for email {}", email);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("Incorrect otp has been entered").build(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("otp verified successfully, now you can change the password").build(), HttpStatus.OK);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Сбрасывает пароль пользователя на основе нового пароля и подтверждения пароля.
     *
     * @param resetPasswordRequest запрос на сброс пароля, содержащий адрес электронной почты и новый пароль.
     * @return ResponseEntity с результатом операции сброса пароля.
     */
    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        String email = resetPasswordRequest.getEmail().trim().toLowerCase();
        String newPassword = resetPasswordRequest.getPassword();
        String confirmPassword = resetPasswordRequest.getConfirmPassword();

        if (!newPassword.equals(confirmPassword)) {
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("Password and confirm password do not match").build(), HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userRepository.findByEmail(email).orElseThrow(
                    ResourceNotFoundException::new
            );
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("Password has been reset successfully").build(), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            log.info("user with email {} not found in the database", email);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("user does not exist with this email").build(), HttpStatus.NOT_FOUND);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Возвращает профиль пользователя по его адресу электронной почты.
     *
     * @param forgotPasswordRequest запрос, содержащий адрес электронной почты пользователя.
     * @return ResponseEntity с профилем пользователя или сообщением об ошибке.
     */
    @Override
    public ResponseEntity<?> myProfile(ForgotPasswordRequest forgotPasswordRequest) {
        String email = forgotPasswordRequest.getEmail().trim().toLowerCase();
        try {
            User user = userRepository.findByEmail(email).orElseThrow(
                    ResourceNotFoundException::new
            );
            return new ResponseEntity<>(UserProfile.builder()
                    .id(user.getId())
//                    .firstName(user.getFirstName())
//                    .lastName(user.getLastName())
                    .email(user.getEmail())
//                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .isOfficiallyEnabled(user.getIsVerified())
                    .build(), HttpStatus.OK);

        } catch (ResourceNotFoundException ex) {
            log.info("user with email {} not found in the Database", email);
            return new ResponseEntity<>(GeneralAPIResponse.builder().message("user does not exist with this email").build(), HttpStatus.NOT_FOUND);
        }
    }
}
