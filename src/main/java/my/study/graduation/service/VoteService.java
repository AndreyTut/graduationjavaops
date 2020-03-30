package my.study.graduation.service;

import my.study.graduation.model.Menu;
import my.study.graduation.model.Vote;
import my.study.graduation.repository.CrudVoteRepository;
import my.study.graduation.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private CrudVoteRepository repository;
    private MenuService menuService;

    @Autowired
    public VoteService(CrudVoteRepository repository, MenuService menuService) {
        this.repository = repository;
        this.menuService = menuService;
    }

    @Transactional
    public void vote(int menuId, int userId) {
        Vote vote = repository.getByUserIdAndVotingDate(userId, LocalDate.now()).orElse(new Vote(userId, LocalDate.now()));
        vote.setMenuId(menuId);
        repository.save(vote);
    }

    public Map<MenuTo, Long> getVotingResult(LocalDate votingDate) {
        List<Vote> votes = repository.getByVotingDate(votingDate);
        List<MenuTo> menuTos = menuService.getForDay(votingDate);
        Map<MenuTo, Long> result = new HashMap<>();
        Map<Integer, Long> voteMap = votes.stream()
                .collect(Collectors.groupingBy(Vote::getMenuId, Collectors.counting()));
        menuTos.forEach(menuTo -> result.put(menuTo, voteMap.get(menuTo.getId())));
        return result;
    }

    public Map<MenuTo, Long> getTodayVotingResult() {
        return getVotingResult(LocalDate.now());
    }

}
