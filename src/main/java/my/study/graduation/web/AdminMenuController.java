package my.study.graduation.web;


import my.study.graduation.model.Menu;
import my.study.graduation.service.MenuService;
import my.study.graduation.to.MenuTo;
import my.study.graduation.util.exceptions.WrongMenuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    private void create(@RequestBody @Valid MenuTo menuTo) {
        menuTo.setDate(LocalDate.now());
        service.create(menuTo);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    private void update(@RequestBody @Valid MenuTo menuTo) {
        service.updateDishes(menuTo);
    }

    @PostMapping("/future")
    @ResponseStatus(HttpStatus.CREATED)
    private void createTomorrow(@RequestBody @Valid MenuTo menuTo,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new WrongMenuException("Date must be future");
        }
        menuTo.setDate(date);
        service.create(menuTo);
    }

    @GetMapping("/{id}")
    private Menu get(@PathVariable int id) {
        return service.get(id);
    }
}
