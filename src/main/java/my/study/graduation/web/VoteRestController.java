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
import java.util.List;

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

    @GetMapping("/admin/voteresult")
    public ResponseEntity<List<RestaurantWithVoices>> getVotes(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return new ResponseEntity<>(service.getVotingResult(date), HttpStatus.OK);
    }

    @GetMapping("/admin/todayvoteresult")
    public ResponseEntity<List<RestaurantWithVoices>> getCurrentVotes() {
        return new ResponseEntity<>(service.getTodayVotingResult(), HttpStatus.OK);
    }

    @PostMapping("/votes")
    public ResponseEntity<?> vote(@RequestParam int menuId, Principal principal) {
        int userId = userService.getId(principal.getName());
        service.vote(menuId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/votes")
    public ResponseEntity<?> changeVote(@RequestParam int menuId, Principal principal) {
        int userId = userService.getId(principal.getName());
        service.changeVote(menuId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/votedmenu")
    public ResponseEntity<MenuTo> getUserVote(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                              Principal principal) {
        int userId = userService.getId(principal.getName());
        MenuTo userVote = service.getUserVote(userId, date);
        return userVote != null
                ? new ResponseEntity<>(userVote, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


