package my.study.graduation.service;

import my.study.graduation.model.Restaurant;
import my.study.graduation.repository.CrudRestaurantRepository;
import my.study.graduation.util.exceptions.NotFoundInDataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RestaurantService {

    private CrudRestaurantRepository repository;

    @Autowired
    public RestaurantService(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "entity must not be null");
        return repository.save(restaurant);
    }


    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant get(int id) {
        return repository.getRestaurantById(id).orElseThrow(() -> new NotFoundInDataBaseException(Restaurant.class, id));
    }

    @Transactional
    public void delete(int id) {
        if (repository.deleteRestaurantById(id) == 0) {
            throw new NotFoundInDataBaseException(Restaurant.class, id);
        }
    }


}
