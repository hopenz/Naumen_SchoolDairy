package ru.naumen.naumen_schooldairy.exception;

/**
 * Исключение, которое выбрасывается, когда запрашиваемый ресурс не найден
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Конструктор по умолчанию для создания нового исключения без сообщения.
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Конструктор для создания нового исключения с заданным сообщением.
     *
     * @param message сообщение об ошибке, которое будет передано при выбрасывании исключения
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Конструктор для создания нового исключения с заданным сообщением и причиной.
     *
     * @param message сообщение об ошибке, которое будет передано при выбрасывании исключения
     * @param cause   причина, по которой было выброшено это исключение
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор для создания нового исключения с заданной причиной.
     *
     * @param cause причина, по которой было выброшено это исключение
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        if (message != null) {
            return (s + ": " + message);
        } else {
            return s;
        }
    }
}
