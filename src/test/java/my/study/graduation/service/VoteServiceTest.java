package my.study.graduation.service;

import my.study.graduation.model.Menu;
import my.study.graduation.to.MenuTo;
import my.study.graduation.util.ToConverters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static my.study.graduation.service.TestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

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
    public void vote() {
        Menu saved = menuService.save(MENU_NEW);
        service.vote(saved.getId(), START_SEQ);
        assertThat(service.getUserVote(START_SEQ, LocalDate.now()))
                .isEqualToComparingFieldByField(ToConverters.menuIntoMenuTo(saved));
    }

    @Test
    public void getVotingResult() {
        Map<MenuTo, Long> map = service.getVotingResult(LocalDate.of(2015, 5, 30));
        assertThat(map.size()).isEqualTo(2);
        assertThat(map).containsValues(2L, 0L);
        assertThat(getKey(map, 0L)).isEqualToComparingFieldByField(MENU_TO_2);
        assertThat(getKey(map, 2L)).isEqualToComparingFieldByField(MENU_TO_1);
    }

    @Test
    public void getUserVote() {
        assertThat(service.getUserVote(100000, LocalDate.of(2015, 5, 30)))
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