package my.study.graduation.service;

import my.study.graduation.model.Restaurant;
import my.study.graduation.util.exceptions.NotFoundInDataBaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static my.study.graduation.service.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {


    @Autowired
    RestaurantService service;

    @Test
    public void save() {
        Restaurant created = new Restaurant("newRestor");
        int id = service.save(created).getId();
        assertThat(service.get(id)).isEqualToComparingFieldByField(created);
        service.save(RESTAURANT_1_UPD);
        assertThat(service.get(RESTAURANT_1.getId())).isEqualToComparingFieldByField(RESTAURANT_1_UPD);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll()).usingFieldByFieldElementComparator()
                .isEqualTo(Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3));
    }

    @Test
    public void get() {
        assertThat(service.get(RESTAURANT_1.getId())).isEqualToComparingFieldByField(RESTAURANT_1);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT_1.getId());
        assertThat(service.getAll().size()).isEqualTo(2);
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(RESTAURANT_2, RESTAURANT_3));
    }

    @Test(expected = NotFoundInDataBaseException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test(expected = NotFoundInDataBaseException.class)
    public void getNotFound() {
        service.get(1);
    }
}