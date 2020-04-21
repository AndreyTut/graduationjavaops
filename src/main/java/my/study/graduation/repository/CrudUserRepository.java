package my.study.graduation.repository;

import my.study.graduation.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudUserRepository extends BaseRepository<User> {

    @Override
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id=:id")
    Optional<User> getById(@Param("id") int id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=:email")
    Optional<User> getByEmail(@Param("email") String email);
}
