package my.study.graduation.repository;

import my.study.graduation.model.Dish;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface CrudDishRepository extends Repository<Dish, Integer> {

    @Transactional
    public void deleteByMenu_Id(int menuId);
}
