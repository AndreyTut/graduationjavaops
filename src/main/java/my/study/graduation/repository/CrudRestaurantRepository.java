package my.study.graduation.repository;

import my.study.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> getRestaurantById(int id);

    int deleteRestaurantById(int id);
}
