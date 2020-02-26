package my.study.graduation.service;

import my.study.graduation.exceptions.NotFoundInDataBase;
import my.study.graduation.model.User;
import my.study.graduation.repository.datajpa.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserService {
    private CrudUserRepository repository;

    @Autowired
    public UserService(CrudUserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }


    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(int id) {
        return repository.getById(id).orElseThrow(() -> new NotFoundInDataBase(User.class, id));
    }

    @Transactional
    public void delete(User user) {
        Assert.notNull(user, "user must not be null");
        if (repository.deleteUserById(user.getId()) == 0) {
            throw new NotFoundInDataBase(user);
        }
    }
}
