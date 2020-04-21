package my.study.graduation.repository;

import my.study.graduation.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface CrudMenuRepository extends Repository<Menu, Integer> {

    @Query("SELECT m FROM Menu m LEFT JOIN FETCH m.dishes LEFT JOIN FETCH m.restaurant WHERE m.id=:id")
    public Optional<Menu> getById(@Param("id") int id);

    @Query("SELECT m FROM Menu m LEFT JOIN FETCH m.dishes LEFT JOIN FETCH m.restaurant WHERE m.day=:localDate")
    public List<Menu> getByDay(@Param("localDate") LocalDate localDate);

    public Menu save(Menu menu);

    @Query("SELECT m FROM Menu m")
    public List<Menu> getAll();
}
