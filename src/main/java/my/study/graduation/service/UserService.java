package my.study.graduation.service;

import my.study.graduation.util.exceptions.*;
import my.study.graduation.model.User;
import my.study.graduation.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {
    private CrudUserRepository repository;

    @Autowired
    public UserService(CrudUserRepository repository) {
        super(repository, User.class);
        this.repository = repository;
    }

    public User get(String email) {
        return repository.getByEmail(email).orElseThrow(() -> new NotFoundInDataBaseException("Not found User with email: " + email));
    }

}
