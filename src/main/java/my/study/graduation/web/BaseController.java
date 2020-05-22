package my.study.graduation.web;

import my.study.graduation.model.AbstractBaseEntity;
import my.study.graduation.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class BaseController<T extends AbstractBaseEntity> extends AbstractBaseControllerExceptionHandler {
    private BaseService<T> service;

    public BaseController(BaseService<T> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> users = service.getAll();
        return users == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable int id) {
        T entity = service.get(id);
        return entity == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody T t) {
        if (t.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(t);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody T t) {
        if (t.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(t);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
