package my.study.graduation.repository.datajpa;

import my.study.graduation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrudUserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);

    Optional<User> getById(int id);

    int deleteUserById(int id);
}
