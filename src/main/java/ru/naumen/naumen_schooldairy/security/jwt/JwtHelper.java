package ru.naumen.naumen_schooldairy.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.naumen.naumen_schooldairy.security.constants.ApplicationConstants;
import ru.naumen.naumen_schooldairy.security.entity.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * Утилита для работы с JSON Web Tokens (JWT).
 * Этот класс предоставляет методы для извлечения данных из токенов,
 * их генерации и проверки действительности.
 */
@Component
public class JwtHelper {

    /**
     * Секретный ключ для подписи токенов
     */
    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * Извлекает данные из JWT-токена, используя предоставленный обработчик.
     *
     * @param jwt            JWT-токен.
     * @param claimsResolver функция, которая обрабатывает извлеченные данные.
     * @param <T>            тип возвращаемого значения.
     * @return результат обработки данных из токена.
     */
    public <T> T extractClaims(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    /**
     * Извлекает имя пользователя из JWT-токена.
     *
     * @param jwt JWT-токен.
     * @return имя пользователя, извлеченное из токена.
     */
    public String extractUsername(String jwt) {
        return extractClaims(jwt, Claims::getSubject);
    }

    /**
     * Генерирует токен доступа для пользователя.
     *
     * @param userDetails детали пользователя.
     * @return сгенерированный токен доступа.
     */
    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", ((User) userDetails).getRole().name());
        return doGenerateAccessToken(claims, userDetails.getUsername());

    }

    /**
     * Генерирует токен обновления для пользователя.
     *
     * @param userDetails детали пользователя.
     * @return сгенерированный токен обновления.
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return doGenerateRefreshToken(userDetails.getUsername());
    }

    /**
     * Генерирует токен обновления на основе имени пользователя.
     *
     * @param username имя пользователя.
     * @return сгенерированный токен обновления.
     */
    private String doGenerateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject("#refresh" + username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.REFRESH_TOKEN_VALIDITY_SECONDS * 1000))
                .setId(UUID.randomUUID().toString())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Генерирует токен доступа на основе предоставленных данных и имени пользователя.
     *
     * @param claims  данные для включения в токен.
     * @param subject имя пользователя (subject).
     * @return сгенерированный токен доступа.
     */
    public String doGenerateAccessToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .setId(UUID.randomUUID().toString())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Проверяет действительность токена доступа по имени пользователя.
     *
     * @param jwt         JWT-токен.
     * @param userDetails детали пользователя для проверки.
     * @return true, если токен действителен; иначе false.
     */
    public Boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername())) && !isJwtExpired(jwt);
    }

    /**
     * Проверяет действительность токена обновления по имени пользователя.
     *
     * @param jwt         JWT-токен обновления.
     * @param userDetails детали пользователя для проверки.
     * @return true, если токен обновления действителен; иначе false.
     */
    public Boolean isRefreshTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt).substring(8);
        return (username.equals(userDetails.getUsername())) && !isJwtExpired(jwt);
    }

    /**
     * Проверяет, истек ли срок действия JWT-токена.
     *
     * @param jwt JWT-токен.
     * @return true, если токен истек; иначе false.
     */
    private boolean isJwtExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    /**
     * Извлекает дату истечения срока действия из JWT-токена.
     *
     * @param jwt JWT-токен.
     * @return дата истечения срока действия токена.
     */
    private Date extractExpiration(String jwt) {
        return extractClaims(jwt, Claims::getExpiration);
    }

    /**
     * Извлекает все данные (claims) из JWT-токена.
     *
     * @param jwt JWT-токен.
     * @return объект Claims, содержащий все данные из токена.
     */
    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * Получает секретный ключ для подписи токенов.
     *
     * @return ключ подписи в виде объекта Key.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
