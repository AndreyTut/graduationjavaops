package my.study.graduation.service;

import my.study.graduation.model.Menu;
import my.study.graduation.to.MenuTo;
import my.study.graduation.to.RestaurantWithVoices;
import my.study.graduation.util.ToConverters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static my.study.graduation.service.TestData.*;
import static org.assertj.core.api.Assertions.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    @Autowired
    private VoteService service;
    @Autowired
    private MenuService menuService;

    @Test
    @Transactional
    public void vote() {
        Menu saved = menuService.save(MENU_NEW);
        service.vote(saved.getId(), START_SEQ);
        assertThat(service.getVotedMenu(START_SEQ, LocalDate.now()))
                .isEqualToComparingFieldByField(ToConverters.menuIntoMenuTo(saved));
    }

    @Test
    public void getVotingResult() {
        List<RestaurantWithVoices> list = service.getVotingResult(LocalDate.of(2015, 5, 30));
        assertThat(list).usingFieldByFieldElementComparator().isEqualTo(voteResults);
    }

    @Test
    public void getUserVote() {
        assertThat(service.getVotedMenu(100000, LocalDate.of(2015, 5, 30)))
                .isEqualToComparingFieldByField(MENU_TO_1);
    }

    private MenuTo getKey(Map<MenuTo, Long> map, Long value) {
        MenuTo result = null;
        for (Map.Entry<MenuTo, Long> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}