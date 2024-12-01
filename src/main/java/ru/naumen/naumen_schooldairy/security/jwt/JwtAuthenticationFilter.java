package ru.naumen.naumen_schooldairy.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Фильтр для аутентификации с использованием JWT.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Утилита для работы с JWT
     */
    private final JwtHelper jwtHelper;

    /**
     * Сервис для загрузки данных пользователя
     */
    private final UserDetailsService userDetailsService;

    /**
     * Метод, который выполняет фильтрацию запросов и аутентификацию пользователя.
     *
     * @param request     объект HttpServletRequest, представляющий HTTP-запрос.
     * @param response    объект HttpServletResponse, представляющий HTTP-ответ.
     * @param filterChain цепочка фильтров для обработки запроса.
     * @throws ServletException если возникает ошибка при обработке сервлетов.
     * @throws IOException      если возникает ошибка ввода-вывода.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token;
        String username = null;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.warn("Authorization header is not there or does not start with Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        token = authorizationHeader.substring(7);
        try {
            username = jwtHelper.extractUsername(token);
        } catch (IllegalStateException e) {
            log.error("Error extracting username from token");
        } catch (ExpiredJwtException e) {
            log.error("Token has expired");
        } catch (MalformedJwtException e) {
            log.error("Token is malformed");
        } catch (Exception e) {
            log.error("An error occurred while extracting username from token");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtHelper.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                log.error("Token is invalid");
            }
        } else {
            log.error("Username is null or Security Context Authentication is not null");
        }
        filterChain.doFilter(request, response);
    }
}
