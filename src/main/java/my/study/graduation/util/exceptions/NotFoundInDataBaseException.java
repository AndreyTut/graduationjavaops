package my.study.graduation.util.exceptions;

import my.study.graduation.model.AbstractBaseEntity;

public class NotFoundInDataBaseException extends RuntimeException {
    public NotFoundInDataBaseException(Class clazz, int id) {
        super(String.format("Entity of class %s with id: %d not found in database", clazz.getSimpleName(), id));
    }

    public NotFoundInDataBaseException(AbstractBaseEntity entity) {
        super(String.format("Entity of class %s with id: %d not found in database", entity.getClass().getSimpleName(), entity.getId()));
    }

    public NotFoundInDataBaseException(String message) {
        super(message);
    }
}
