package ru.naumen.naumen_schooldairy.security.constants;

/**
 * Класс, содержащий константы, используемые в приложении
 */
public class ApplicationConstants {

    /**
     * Символы, используемые для генерации одноразовых паролей (OTP)
     */
    public static final String OTP_CHARACTERS = "123456789";

    /**
     * Длина одноразового пароля (OTP)
     */
    public static final Integer OTP_LENGTH = 6;

    /**
     * Время действия токена доступа в секундах
     */
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60;

    /**
     * Время действия токена обновления в секундах
     */
    public static final long REFRESH_TOKEN_VALIDITY_SECONDS = 30 * 24 * 60 * 60;


}
