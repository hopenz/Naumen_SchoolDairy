package ru.naumen.naumen_schooldairy.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Глобальный обработчик исключений для контроллеров.
 * Обрабатывает различные типы исключений и возвращает соответствующие ответы с ошибками.
 */
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * Обрабатывает исключения валидации, возникающие при неверных аргументах метода.
     *
     * @param ex исключение, содержащее информацию о валидации.
     * @return карта с ошибками валидации, где ключом является имя поля, а значением - сообщение об ошибке.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    /**
     * Обрабатывает исключения нарушения ограничений, возникающие при нарушении правил валидации.
     *
     * @param ex исключение, содержащее информацию о нарушениях ограничений.
     * @return карта с ошибками, где ключом является путь к свойству, а значением - сообщение об ошибке.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
        return errors;
    }

    /**
     * Обрабатывает исключения, возникающие при отсутствии сущности в базе данных.
     *
     * @param ex исключение, содержащее сообщение об отсутствии сущности.
     * @return карта с сообщением об ошибке.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }
}
