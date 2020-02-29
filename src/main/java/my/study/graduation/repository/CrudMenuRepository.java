package my.study.graduation.repository;

import my.study.graduation.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface CrudMenuRepository extends Repository<Menu, Integer> {

    public Optional<Menu> getById(int id);

    public List<Menu> getByDay(LocalDate localDate);

    public Menu save(Menu menu);
}
