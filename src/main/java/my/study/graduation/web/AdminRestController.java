package my.study.graduation.web;

import my.study.graduation.model.User;
import my.study.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("rest/admin/users")
public class AdminRestController extends AbstractBaseControllerExceptionHandler {

    private final UserService service;

    @Autowired
    public AdminRestController(UserService service) {
        this.service = service;
    }


    @GetMapping("/by")
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        User user = service.get(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        User user = service.get(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user) {
        return save(user, u -> u.getId() != null, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody User user) {
        return save(user, u -> u.getId() == null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    private ResponseEntity<?> save(User user, Predicate<User> checkerIfUserNew, HttpStatus successStatus) {
        if (checkerIfUserNew.test(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(user);
        return new ResponseEntity<>(successStatus);
    }
}
