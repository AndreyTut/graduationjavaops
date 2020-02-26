package my.study.graduation.exceptions;

import my.study.graduation.model.AbstractBaseEntity;

public class NotFoundInDataBase extends RuntimeException {
    public NotFoundInDataBase(Class clazz, int id) {
        super(String.format("Entity of class %s with id: %d not found in database", clazz.getSimpleName(), id));
    }

    public NotFoundInDataBase(AbstractBaseEntity entity) {
        super(String.format("Entity of class %s with id: %d not found in database", entity.getClass().getSimpleName(), entity.getId()));
    }
}
