package my.study.graduation.web;

import my.study.graduation.model.Menu;
import my.study.graduation.repository.CrudMenuRepository;
import my.study.graduation.service.MenuService;
import my.study.graduation.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("rest/menus")
public class MenuRestController extends AbstractBaseControllerExceptionHandler {

    private MenuService service;

    //TODO delete after debugging
    @Autowired
    private CrudMenuRepository repository;

    @Autowired
    public MenuRestController(MenuService service) {
        this.service = service;
    }

    @GetMapping("/bydate")
    private ResponseEntity<List<MenuTo>> getForDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<MenuTo> list = service.getForDay(date);
        return list != null & !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/today")
    private ResponseEntity<List<MenuTo>> getToday() {
        List<MenuTo> list = service.getForToday();
        return list != null & !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //TODO delete after debugging
    @GetMapping
    private List<Menu> getAll() {
        return repository.getAll();
    }
}
