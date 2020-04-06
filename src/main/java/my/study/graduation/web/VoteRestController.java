package my.study.graduation.web;

import my.study.graduation.service.VoteService;
import my.study.graduation.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@RestController
@RequestMapping("/votes")
public class VoteRestController {

    private VoteService service;

    @Autowired
    public VoteRestController(VoteService service) {
        this.service = service;
    }

    //fixme not serialize MenuTo into JSON, just in String
    @GetMapping
    public ResponseEntity<Map<MenuTo, Long>> getVotes(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        Map<MenuTo, Long> map = service.getVotingResult(localDate);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<Map<MenuTo, Long>> getCurrentVotes() {
        Map<MenuTo, Long> map = service.getTodayVotingResult();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/vote")
    public ResponseEntity<?> vote(@RequestParam int userId, @RequestParam int menuId) {
        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        service.vote(menuId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/uservote")
    public ResponseEntity<MenuTo> getUserVote(@RequestParam int id,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        MenuTo userVote = service.getUserVote(id, date);
        return userVote != null
                ? new ResponseEntity<>(userVote, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


