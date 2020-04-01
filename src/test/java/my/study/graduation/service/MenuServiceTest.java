package my.study.graduation.service;

import my.study.graduation.model.Menu;
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

import static my.study.graduation.service.TestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuServiceTest {

    @Autowired
    private MenuService service;

    @Test
    public void getForDay() {
        assertThat(service.getForDay(LocalDate.of(2015, 5, 30)))
                .usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(MENU_TO_1, MENU_TO_2));
    }

    @Test
    public void save() {
        Menu saved = service.save(MENU_NEW);
        assertThat(service.get(saved.getId())).usingRecursiveComparison().isEqualTo(MENU_NEW);
        service.save(MENU_UPD);
        assertThat(service.get(MENU.getId())).usingRecursiveComparison().isEqualTo(MENU_UPD);
    }

    @Test
    public void get() {
        assertThat(service.get(MENU.getId())).usingRecursiveComparison().isEqualTo(MENU);
    }
}