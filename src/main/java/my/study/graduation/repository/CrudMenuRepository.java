package my.study.graduation.repository;

import my.study.graduation.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface CrudMenuRepository extends Repository<Menu, Integer> {

    public Optional<Menu> getById(int id);


//fixme n+1 problem
  //  @Query("SELECT m FROM Menu m LEFT JOIN FETCH m.dishes LEFT JOIN FETCH m.restaurant WHERE m.day=:localDate")
//    @Query("SELECT m FROM Menu m LEFT JOIN FETCH m.dishes WHERE m.day=:localDate")
    public List<Menu> getByDay(@Param("localDate") LocalDate localDate);

    public Menu save(Menu menu);

    //TODO delite this method after debugging
    @Query("SELECT m FROM Menu m")
    public List<Menu> getAll();
}
