package my.study.graduation.web;

import my.study.graduation.model.User;
import my.study.graduation.service.UserService;
import my.study.graduation.util.ErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("rest/profile")
public class ProfileRestController {

    private UserService service;

    @Autowired
    public ProfileRestController(UserService service) {
        this.service = service;
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid @RequestBody User user, Principal principal) {
        Integer id = service.getId(principal.getName());
        if (!id.equals(user.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        service.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Principal principal) {
        service.delete(service.getId(principal.getName()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<User> get(Principal principal) {
        return new ResponseEntity<>(service.get(principal.getName()), HttpStatus.OK);
    }
}
