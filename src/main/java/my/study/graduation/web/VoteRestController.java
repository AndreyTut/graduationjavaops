package my.study.graduation.web;

import my.study.graduation.service.UserService;
import my.study.graduation.service.VoteService;
import my.study.graduation.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("rest/")
public class VoteRestController extends AbstractBaseControllerExceptionHandler {

    private VoteService service;

    private UserService userService;

    @Autowired
    public VoteRestController(VoteService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/votes")
    @ResponseStatus(HttpStatus.CREATED)
    public void vote(@RequestParam int menuId, Principal principal) {
        int userId = userService.getId(principal.getName());
        service.vote(menuId, userId);
    }

    @PutMapping("/votes")
    @ResponseStatus(HttpStatus.OK)
    public void changeVote(@RequestParam int menuId, Principal principal) {
        int userId = userService.getId(principal.getName());
        service.changeVote(menuId, userId);
    }

    @GetMapping("/votedmenu")
    public ResponseEntity<MenuTo> getVotedMenu(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                               Principal principal) {
        int userId = userService.getId(principal.getName());
        MenuTo userVote = service.getVotedMenu(userId, date);
        return userVote != null
                ? new ResponseEntity<>(userVote, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


