package my.study.graduation.service;

import my.study.graduation.model.Vote;
import my.study.graduation.repository.CrudVoteRepository;
import my.study.graduation.to.MenuTo;
import my.study.graduation.to.RestaurantWithVoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static my.study.graduation.util.ToConverters.menuIntoMenuTo;

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

    public List<RestaurantWithVoices> getVotingResult(LocalDate votingDate) {
        List<Vote> votes = repository.getByVotingDate(votingDate);
        List<MenuTo> menuTos = menuService.getForDay(votingDate);
        List<RestaurantWithVoices> resultList = new ArrayList<>();
        Map<Integer, Long> voteMap = votes.stream()
                .collect(Collectors.groupingBy(Vote::getMenuId, Collectors.counting()));
        menuTos.forEach(menuTo -> resultList.add(new RestaurantWithVoices(menuTo.getRestaurant(),
                voteMap.get(menuTo.getId()) == null ? 0 : voteMap.get(menuTo.getId()).intValue())));
        resultList.sort((r1, r2) -> r2.getVoises() - r1.getVoises());
        return resultList;
    }

    public List<RestaurantWithVoices> getTodayVotingResult() {
        return getVotingResult(LocalDate.now());
    }

    public MenuTo getUserVote(int id, LocalDate localDate) {
        Optional<Vote> userVote = repository.getByUserIdAndVotingDate(id, localDate);
        return userVote.map(vote -> menuIntoMenuTo(menuService.get(vote.getMenuId()))).orElse(null);
    }
}
