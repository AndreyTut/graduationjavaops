package my.study.graduation.repository;

import my.study.graduation.model.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
@NoRepositoryBean
public interface BaseRepository<T extends AbstractBaseEntity> extends JpaRepository<T, Integer> {

    Optional<T> getById(int id);

    int deleteEntityById(int id);
}
