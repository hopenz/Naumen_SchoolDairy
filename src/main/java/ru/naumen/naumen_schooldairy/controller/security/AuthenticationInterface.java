package ru.naumen.naumen_schooldairy.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.naumen.naumen_schooldairy.security.dto.requests.*;
import ru.naumen.naumen_schooldairy.security.dto.responses.GeneralAPIResponse;
import ru.naumen.naumen_schooldairy.security.dto.responses.RefreshTokenResponse;
import ru.naumen.naumen_schooldairy.security.dto.responses.RegisterResponse;
import ru.naumen.naumen_schooldairy.security.dto.responses.RegisterVerifyResponse;

/**
 * Интерфейс для определения API аутентификации
 */
@Tag(name = "Authentication", description = "Authentication APIs")
public interface AuthenticationInterface {

    /**
     * Регистрация нового пользователя.
     * Этот API регистрирует нового пользователя, если пользователь еще не существует в записях,
     * и отправляет электронное письмо пользователю для верификации.
     *
     * @param registerRequest объект запроса с данными для регистрации
     * @return ResponseEntity с результатом регистрации
     */
    @Operation(summary = "Регистрация нового пользователя",
            description = "Этот API регистрирует нового пользователя, если он еще не указан в записях, и отправляет ему электронное письмо для подтверждения.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User has been saved in records, but still needs to be verified",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = """
                        Bad Request:
                        - User already exists with this email and is verified.
                        - Validation errors:
                          - First name can't be blank
                          - Last name can't be blank
                          - Invalid email entered
                          - Password must contain at least 8 characters, one uppercase, one lowercase and one number
                          - Please choose your gender
                          - Invalid phone number, please enter in the format +(code)XXXXXXXXXX
                          - Please choose a role
                        - User already exists with this phone number. Please try again with a different phone number.
                    """,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error: Failed to send OTP email. Please try again later.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))
    })
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest);

    /**
     * Верификация регистрации пользователя.
     * Этот API проверяет регистрацию пользователя с использованием предоставленного OTP.
     *
     * @param registerVerifyRequest объект запроса с данными для верификации
     * @return ResponseEntity с результатом верификации
     */
    @Operation(summary = "Подтвердите регистрацию пользователя", description = "Этот API проверяет регистрацию пользователя с помощью предоставленного OTP.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registration verified successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterVerifyResponse.class))),
            @ApiResponse(responseCode = "400", description = """
                        Bad Request:
                        - Email or OTP is incorrect
                    """,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "408", description = "Request Timeout: OTP has expired",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: User with the specified email not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error: Failed to verify user registration",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class)))
    })
    ResponseEntity<?> verifyRegistration(@Valid @RequestBody RegisterVerifyRequest registerVerifyRequest);

    /**
     * Вход пользователя в систему.
     * Аутентифицирует и выполняет вход пользователя.
     *
     * @param loginRequest объект запроса с данными для входа
     * @return ResponseEntity с результатом входа
     */
    @Operation(summary = "Авторизация пользователя", description = "Выполнить аутентификацию и вход в систему пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterVerifyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid credentials or user not verified",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: User with the specified email not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class)))
    })
    ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest);

    /**
     * Запрос на сброс пароля.
     * Отправляет OTP на электронную почту пользователя для сброса пароля.
     *
     * @param forgotPasswordRequest объект запроса с данными для сброса пароля
     * @return ResponseEntity с результатом отправки OTP
     */
    @Operation(summary = "Забыл пароль", description = "Отправить OTP на электронную почту пользователя для сброса пароля.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP sent successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: User with the specified email not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "429", description = "Too Many Requests: OTP already sent recently, wait for 2 minutes before trying again",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error: Failed to send OTP email",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class)))
    })
    ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest);

    /**
     * Верификация OTP.
     * Проверяет введенный OTP пользователя.
     *
     * @param registerVerifyRequest объект запроса с данными для верификации OTP
     * @return ResponseEntity с результатом верификации OTP
     */
    @Operation(summary = "Верификация OTP", description = "Проверяет введенный OTP пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP verified successfully, now you can change the password",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request: Incorrect OTP entered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: User with the specified email not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "408", description = "Request Timeout: OTP has expired",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class)))
    })
    ResponseEntity<?> verifyOtp(@Valid @RequestBody RegisterVerifyRequest registerVerifyRequest);

    /**
     * Сброс пароля пользователя.
     * Этот API позволяет сбросить пароль для пользователя.
     *
     * @param resetPasswordRequest объект запроса с данными для сброса пароля
     * @return ResponseEntity с результатом сброса пароля
     */
    @Operation(summary = "Сброс пароля", description = "Сбросить пароль пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password has been reset successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request: Password and confirm password do not match",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: User with the specified email not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class)))
    })
    ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest);

    /**
     * Обновление токена доступа.
     * Этот API генерирует новый токен доступа на основе refresh token.
     *
     * @param refreshToken строка refresh token для генерации нового токена доступа
     * @return ResponseEntity с новым токеном доступа
     */
    @Operation(summary = "Обновление токена", description = "Генерирует новый токен доступа на основе refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Access token generated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RefreshTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid refresh token or refresh token expired",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class)))
    })
    ResponseEntity<?> refreshToken(@RequestParam(name = "refreshToken") String refreshToken);
}
