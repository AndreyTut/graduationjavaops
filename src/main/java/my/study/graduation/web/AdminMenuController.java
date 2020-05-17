package my.study.graduation.web;


import my.study.graduation.model.Menu;
import my.study.graduation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("rest/admin/menus")
public class AdminMenuController extends AbstractBaseControllerExceptionHandler {

    private MenuService service;

    @Autowired
    public AdminMenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping
    private ResponseEntity<Menu> create(@RequestBody @Valid Menu menu) {
        menu.setDay(LocalDate.now());
        service.save(menu);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private Menu get(@PathVariable int id) {
        return service.get(id);
    }

}
