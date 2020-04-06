package my.study.graduation.web;

import my.study.graduation.model.Menu;
import my.study.graduation.repository.CrudMenuRepository;
import my.study.graduation.service.MenuService;
import my.study.graduation.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuRestController {

    private MenuService service;

    //TODO delete after ebugging
    @Autowired
    private CrudMenuRepository repository;

    @Autowired
    public MenuRestController(MenuService service) {
        this.service = service;
    }

    @GetMapping("/bydate")
    private ResponseEntity<List<MenuTo>> getForDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        List<MenuTo> list = service.getForDay(localDate);
        return list != null & !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/today")
    private ResponseEntity<List<MenuTo>> getToday() {
        List<MenuTo> list = service.getForDay(LocalDate.now());
        return list != null & !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<Menu> create(@RequestBody Menu menu) {
        menu.setDay(LocalDate.now());
        service.save(menu);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private Menu get(@PathVariable int id){
        return service.get(id);
    }

    //TODO delete after debugging
    @GetMapping
    private List<Menu> getAll(){
        return repository.getAll();
    }
}
