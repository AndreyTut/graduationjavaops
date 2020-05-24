package my.study.graduation.web;

import my.study.graduation.service.VoteService;
import my.study.graduation.to.RestaurantWithVoices;
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
@RequestMapping("/rest/admin")
public class AdminVoteController {

    private final VoteService service;

    @Autowired
    public AdminVoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping("/voteresult")
    public ResponseEntity<List<RestaurantWithVoices>> getVotes(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return new ResponseEntity<>(service.getVotingResult(date), HttpStatus.OK);
    }

    @GetMapping("/todayvoteresult")
    public ResponseEntity<List<RestaurantWithVoices>> getCurrentVotes() {
        return new ResponseEntity<>(service.getTodayVotingResult(), HttpStatus.OK);
    }

}
