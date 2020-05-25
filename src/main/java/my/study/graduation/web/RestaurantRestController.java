package my.study.graduation.web;

import my.study.graduation.model.Restaurant;
import my.study.graduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("rest/admin/restaurants")
public class RestaurantRestController extends AbstractBaseControllerExceptionHandler {

    private RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        List<Restaurant> restaurants = service.getAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        Restaurant entity = service.get(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant restaurant) {
        return save(restaurant, r -> r.getId() != null, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Restaurant restaurant) {
        return save(restaurant, r -> r.getId() == null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    private ResponseEntity<?> save(Restaurant restaurant, Predicate<Restaurant> checkerIfRestaurantNew,
                                   HttpStatus successStatus) {
        if (checkerIfRestaurantNew.test(restaurant)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(restaurant);
        return new ResponseEntity<>(successStatus);
    }


}
