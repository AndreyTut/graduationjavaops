package my.study.graduation.service;

import my.study.graduation.model.Restaurant;
import my.study.graduation.repository.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService extends BaseService<Restaurant> {

    @Autowired
    public RestaurantService(CrudRestaurantRepository repository) {
        super(repository, Restaurant.class);
    }

}
