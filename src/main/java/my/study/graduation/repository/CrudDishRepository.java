package my.study.graduation.repository;

import my.study.graduation.model.Dish;
import org.springframework.data.repository.Repository;

public interface CrudDishRepository extends Repository<Dish, Integer> {

    public void deleteByMenu_Id(int menuId);
}
