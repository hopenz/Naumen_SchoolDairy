package ru.naumen.naumen_schooldairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения NaumenSchoolDairy.
 * Этот класс запускает приложение Spring Boot.
 */
@SpringBootApplication
public class NaumenSchoolDairyApplication {

    /**
     * Главный метод приложения.
     *
     * @param args аргументы командной строки, передаваемые при запуске приложения.
     */
    public static void main(String[] args) {
        SpringApplication.run(NaumenSchoolDairyApplication.class, args);
    }

}
