package my.study.graduation.service;

import my.study.graduation.model.Role;
import my.study.graduation.model.User;
import my.study.graduation.util.exceptions.NotFoundInDataBaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.EnumSet;

import static my.study.graduation.service.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {


    @Autowired
    UserService service;


    @Test
    public void save() {
        User created = new User("test", "test@test.com", "psswor", EnumSet.of(Role.ROLE_USER), true);
        int id = service.save(created).getId();
        assertThat(service.get(id)).isEqualToComparingFieldByField(created);
        service.save(USER_UPD);
        assertThat(service.get(USER.getId())).isEqualToComparingFieldByField(USER_UPD);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(USER, ADMIN));
    }

    @Test
    public void get() {
        User user = service.get(USER.getId());
        assertThat(user).isEqualToComparingFieldByField(USER);
    }

    @Test
    public void delete() {
        service.delete(USER.getId());
        assertThat(service.getAll().size()).isEqualTo(1);
        assertThat(service.getAll().get(0)).isEqualToComparingFieldByField(ADMIN);
    }

    @Test
    public void getByEmail() {
        System.out.println(service.getAll());
        User user = service.get(USER.getEmail());
        assertThat(user).isEqualToComparingFieldByField(USER);
    }

    @Test(expected = NotFoundInDataBaseException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test(expected = NotFoundInDataBaseException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMail() {
        service.save(new User("test", USER.getEmail(), "password", EnumSet.of(Role.ROLE_USER), true));
    }

}