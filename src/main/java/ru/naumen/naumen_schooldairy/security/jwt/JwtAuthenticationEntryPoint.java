package ru.naumen.naumen_schooldairy.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Класс, реализующий интерфейс AuthenticationEntryPoint.
 * Этот компонент обрабатывает неавторизованные запросы, отправляя ответ с кодом 401 (Unauthorized).
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Метод, который вызывается, когда пользователь пытается получить доступ к защищенному ресурсу
     * без предоставления действительных учетных данных.
     *
     * @param request       объект HttpServletRequest, представляющий HTTP-запрос.
     * @param response      объект HttpServletResponse, представляющий HTTP-ответ.
     * @param authException исключение аутентификации, содержащее информацию об ошибке.
     * @throws IOException      если возникает ошибка при обработке запроса или ответа.
     * @throws ServletException если возникает ошибка при обработке сервлетов.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are unauthorized");
    }
}
