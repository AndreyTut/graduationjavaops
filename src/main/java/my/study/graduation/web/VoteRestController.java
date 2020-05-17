package my.study.graduation.web;

import my.study.graduation.service.UserService;
import my.study.graduation.service.VoteService;
import my.study.graduation.to.MenuTo;
import my.study.graduation.to.RestaurantWithVoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("rest/votes")
public class VoteRestController extends AbstractBaseControllerExceptionHandler {

    private VoteService service;

    private UserService userService;

    @Autowired
    public VoteRestController(VoteService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantWithVoices>> getVotes(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return new ResponseEntity<>(service.getVotingResult(date), HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<List<RestaurantWithVoices>> getCurrentVotes() {
        return new ResponseEntity<>(service.getTodayVotingResult(), HttpStatus.OK);
    }

    @PostMapping("/vote")
    public ResponseEntity<?> vote(@RequestParam int menuId, Principal principal) {
        int userId = userService.getId(principal.getName());
        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        service.vote(menuId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/uservote")
    public ResponseEntity<MenuTo> getUserVote(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                              Principal principal) {
        int userId = userService.getId(principal.getName());
        MenuTo userVote = service.getUserVote(userId, date);
        return userVote != null
                ? new ResponseEntity<>(userVote, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


