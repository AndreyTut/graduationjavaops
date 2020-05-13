package my.study.graduation.web;

import my.study.graduation.model.User;
import my.study.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/admin/users")
public class AdminRestController extends BaseController<User> {

    private final UserService service;

    @Autowired
    public AdminRestController(UserService service) {
        super(service);
        this.service = service;
    }


    @GetMapping("/by")
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        User user = service.get(email);
        return user == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(user, HttpStatus.OK);
    }
}
