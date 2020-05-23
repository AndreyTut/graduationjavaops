package my.study.graduation.web;


import my.study.graduation.model.Menu;
import my.study.graduation.service.MenuService;
import my.study.graduation.to.MenuTo;
import my.study.graduation.util.exceptions.WrongMenuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    private ResponseEntity<MenuTo> create(@RequestBody @Valid MenuTo menuTo) {
        menuTo.setDate(LocalDate.now());
        service.create(menuTo);
        return new ResponseEntity<>(menuTo, HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<MenuTo> update(@RequestBody @Valid MenuTo menuTo) {
        service.updateDishes(menuTo);
        return new ResponseEntity<>(menuTo, HttpStatus.OK);
    }

    @PostMapping("/future")
    private ResponseEntity<MenuTo> createTomorrow(@RequestBody @Valid MenuTo menuTo,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new WrongMenuException("Date must be future");
        }
        menuTo.setDate(date);
        service.create(menuTo);
        return new ResponseEntity<>(menuTo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private Menu get(@PathVariable int id) {
        return service.get(id);
    }
}
