package my.study.graduation.repository;

import my.study.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> getRestaurantById(int id);

    @Transactional
    int deleteRestaurantById(int id);
}
