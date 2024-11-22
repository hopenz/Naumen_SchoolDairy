package ru.naumen.naumen_schooldairy.exception;

/**
 * Исключение, выбрасываемое при отсутствии сущности в базе данных.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Конструктор исключения EntityNotFoundException.
     *
     * @param entity название сущности, которая не была найдена.
     * @param id     уникальный идентификатор сущности, которая не была найдена.
     */
    public EntityNotFoundException(String entity, Long id) {
        super(entity + " with id " + id + " not found");
    }
}
