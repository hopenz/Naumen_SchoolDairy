package ru.naumen.naumen_schooldairy.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumen.naumen_schooldairy.security.dto.requests.ForgotPasswordRequest;
import ru.naumen.naumen_schooldairy.security.dto.responses.GeneralAPIResponse;
import ru.naumen.naumen_schooldairy.security.dto.responses.UserProfile;
import ru.naumen.naumen_schooldairy.security.service.AuthenticationService;

/**
 * Контроллер для обработки запросов, связанных с профилем пользователя
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final AuthenticationService authenticationService;


    /**
     * Получение информации о профиле пользователя.
     * Этот API позволяет пользователю получить информацию о своем профиле.
     *
     * @param forgotPasswordRequest объект запроса с данными для получения профиля (например, email)
     * @return ResponseEntity с информацией о профиле пользователя или ошибкой, если пользователь не найден
     */
    @Operation(summary = "Мой профиль", description = "Получение информации о профиле пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: User profile retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfile.class))),
            @ApiResponse(responseCode = "404", description = "Not Found: User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneralAPIResponse.class))),

    })
    @PostMapping("/myProfile")
    public ResponseEntity<?> myProfile(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        log.info("My profile request received for email {}", forgotPasswordRequest.getEmail());
        return authenticationService.myProfile(forgotPasswordRequest);
    }
}
