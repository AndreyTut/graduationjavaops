import javassist.NotFoundException;
import my.study.graduation.repository.CrudVoteRepository;
import my.study.graduation.service.VoteService;
import my.study.graduation.to.MenuTo;
import my.study.graduation.util.exceptions.*;
import my.study.graduation.model.*;
import my.study.graduation.repository.CrudMenuRepository;
import my.study.graduation.service.MenuService;
import my.study.graduation.service.RestaurantService;
import my.study.graduation.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class SpringStarter {
    public static void main(String[] args) throws NotFoundException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-db.xml", "spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
        UserService service = context.getBean(UserService.class);
        User user = service.save(new User("Dmytro", "emael@com.com", "efefefc", EnumSet.of(Role.ROLE_USER), true));
        User user1 = service.save(new User("petro", "petro@com.com", "efefefc", EnumSet.of(Role.ROLE_USER), true));
        User user2 = service.save(new User("vaso", "vaso+@com.com", "efefefc", EnumSet.of(Role.ROLE_USER), true));
        User user3 = service.get(100001);
        printItems(service.getAll());
//        CrudMenuRepository repository = context.getBean(CrudMenuRepository.class);
        MenuService menuService = context.getBean(MenuService.class);
        Menu menu = new Menu()
        List<MenuTo> menus = menuService.getForToday();// (LocalDate.of(2015, 5, 30));
        printItems(menus);
    }

    private static <T> void printItems(List<T> list) {
        list.forEach(System.out::println);
    }
}
