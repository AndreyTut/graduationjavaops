package my.study.graduation.web;

import my.study.graduation.model.User;
import my.study.graduation.service.UserService;
import my.study.graduation.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("rest/profile")
public class ProfileRestController extends AbstractBaseControllerExceptionHandler {

    private UserService service;

    @Autowired
    public ProfileRestController(UserService service) {
        this.service = service;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody UserTo userTo, Principal principal) {
        Integer id = service.getId(principal.getName());
        userTo.setId(id);
        service.save(userTo);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Principal principal) {
        service.delete(service.getId(principal.getName()));
    }

    @GetMapping
    public ResponseEntity<User> get(Principal principal) {
        return new ResponseEntity<>(service.get(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserTo userTo) {
        service.save(userTo);
    }

}
