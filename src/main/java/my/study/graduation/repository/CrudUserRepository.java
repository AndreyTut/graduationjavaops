package my.study.graduation.repository;

import my.study.graduation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id=:id")
    Optional<User> getById(@Param("id") int id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=:email")
    Optional<User> getByEmail(@Param("email") String email);

    @Query("SELECT u.id FROM User u WHERE u.email=:email")
    Optional<Integer> getIdByEmail(@Param("email") String email);

    @Transactional
    int deleteUserById(int id);
}
