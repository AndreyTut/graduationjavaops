package my.study.graduation.service;

import my.study.graduation.model.Vote;
import my.study.graduation.repository.CrudVoteRepository;
import my.study.graduation.to.MenuTo;
import my.study.graduation.to.RestaurantWithVoices;
import my.study.graduation.util.exceptions.VotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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
    //TODO Change to 11.00 after testing
    private final LocalTime CRITICAL_TIME = LocalTime.of(23, 59);

    @Autowired
    public VoteService(CrudVoteRepository repository, MenuService menuService) {
        this.repository = repository;
        this.menuService = menuService;
    }

    @Transactional
    public void vote(int menuId, int userId) {
        if (repository.getByUserIdAndVotingDate(userId, LocalDate.now()).isPresent()) {
            throw new VotingException(String.format("User with id %d already voted ", userId));
        }
        if (!containsWithId(menuService.getForToday(), menuId)) {
            throw new VotingException("User can vote only for today menu");
        }
        repository.save(new Vote(userId, menuId, LocalDate.now()));
    }

    @Transactional
    public void changeVote(int menuId, int userId) {
        if (LocalTime.now().isAfter(CRITICAL_TIME)) {
            throw new VotingException("Too late to change vote");
        }

        if (!containsWithId(menuService.getForToday(), menuId)) {
            throw new VotingException("User can vote only for today menu");
        }

        Vote vote = repository.getByUserIdAndVotingDate(userId, LocalDate.now())
                .orElseThrow(() -> new VotingException(String.format("User with id %d hasn't voted yet", userId)));
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

    private boolean containsWithId(List<MenuTo> list, int menuId) {
        for (MenuTo menuTo : list) {
            if (menuTo.getId() == menuId) {
                return true;
            }
        }
        return false;
    }

}
