package my.study.graduation.repository;

import my.study.graduation.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudUserRepository extends BaseRepository<User> {

    Optional<User> getByEmail(String email);
}
