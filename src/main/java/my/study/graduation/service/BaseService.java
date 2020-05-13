package my.study.graduation.service;

import my.study.graduation.util.exceptions.*;
import my.study.graduation.model.AbstractBaseEntity;
import my.study.graduation.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseService<T extends AbstractBaseEntity> {

    private BaseRepository<T> repository;

    private final Class<T> type;

    public BaseService(BaseRepository<T> repository, Class<T> type) {
        this.repository = repository;
        this.type = type;
    }

    @Transactional
    public T save(T t) {
        Assert.notNull(t, "entity must not be null");
        return repository.save(t);
    }


    public List<T> getAll() {
        return repository.findAll();
    }

    public T get(int id) {
        return repository.getById(id).orElseThrow(() -> new NotFoundInDataBaseException(type, id));
    }

    @Transactional
    public void delete(int id) {
        if (repository.deleteEntityById(id) == 0) {
            throw new NotFoundInDataBaseException(type, id);
        }
    }

}
