package my.study.graduation.web;

import my.study.graduation.service.MenuService;
import my.study.graduation.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("rest/menus")
public class MenuRestController extends AbstractBaseControllerExceptionHandler {

    private MenuService service;

    @Autowired
    public MenuRestController(MenuService service) {
        this.service = service;
    }

    @GetMapping("/bydate")
    private ResponseEntity<List<MenuTo>> getForDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<MenuTo> list = service.getForDay(date);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/today")
    private ResponseEntity<List<MenuTo>> getToday() {
        List<MenuTo> list = service.getForToday();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
