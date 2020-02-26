package my.study.graduation.repository.datajpa;

import my.study.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
