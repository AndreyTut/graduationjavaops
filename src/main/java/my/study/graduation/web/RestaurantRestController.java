package my.study.graduation.web;

import my.study.graduation.model.Restaurant;
import my.study.graduation.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController extends BaseController<Restaurant> {

    @Autowired
    public RestaurantRestController(BaseService<Restaurant> service) {
        super(service);
    }
}
