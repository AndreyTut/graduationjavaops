import javassist.NotFoundException;
import my.study.graduation.repository.CrudVoteRepository;
import my.study.graduation.service.VoteService;
import my.study.graduation.to.MenuTo;
import my.study.graduation.util.ToConverters;
import my.study.graduation.util.exceptions.*;
import my.study.graduation.model.*;
import my.study.graduation.repository.CrudMenuRepository;
import my.study.graduation.service.MenuService;
import my.study.graduation.service.RestaurantService;
import my.study.graduation.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class SpringStarter {
    public static void main(String[] args) throws NotFoundException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-db.xml", "spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
        UserService service = context.getBean(UserService.class);
//        RestaurantService restaurantService = context.getBean(RestaurantService.class);
//        User user = service.save(new User("Dmytro", "email@com.com", "efefefc", EnumSet.of(Role.ROLE_USER), true));
//        User user1 = service.save(new User("petro", "petro@com.com", "efefefc", EnumSet.of(Role.ROLE_USER), true));
//        User user2 = service.save(new User("vaso", "vasv@gmail.com", "efefefc", EnumSet.of(Role.ROLE_USER), true));
//        User user3 = service.get(100001);
//        Restaurant restaurant = restaurantService.get(100002);
//        System.out.println(user3);
//        System.out.println(restaurant);
//        printItems(service.getAll());
//        CrudMenuRepository repository = context.getBean(CrudMenuRepository.class);
        MenuService menuService = context.getBean(MenuService.class);
        System.out.println(menuService.getForDay(LocalDate.of(2015, 5, 30)));
//        RestaurantService restaurantService = context.getBean(RestaurantService.class);
//        List<Restaurant> restaurants = restaurantService.getAll();
//        List<Dish> dishes = new ArrayList<>();
//        dishes.add(new Dish("sup", 50));
//        dishes.add(new Dish("potato", 40));
//        dishes.add(new Dish("tea", 30));
//
//        List<Dish> dishes1 = new ArrayList<>();
//        dishes1.add(new Dish("sup", 50));
//        dishes1.add(new Dish("potato", 40));
//        dishes1.add(new Dish("tea", 30));
//
//        List<Dish> dishes2 = new ArrayList<>();
//        dishes2.add(new Dish("sup", 50));
//        dishes2.add(new Dish("potato", 40));
//        dishes2.add(new Dish("tea", 30));
//        Menu menu = new Menu(restaurants.get(0), dishes, LocalDate.now());
//        Menu menu1 = new Menu(restaurants.get(1), dishes1, LocalDate.now());
//        Menu menu2 = new Menu(restaurants.get(2), dishes2, LocalDate.now());
//        menuService.save(menu);
//        menuService.save(menu1);
//        menuService.save(menu2);
//        List<MenuTo> menus = menuService.getForToday();// (LocalDate.of(2015, 5, 30));
//        printItems(menus);
//
//        VoteService voteService = context.getBean(VoteService.class);
//        voteService.vote(menu.getId(), user.getId());
//        voteService.vote(menu.getId(), user1.getId());
//        voteService.vote(menu1.getId(), user2.getId());
//        voteService.vote(menu2.getId(), user3.getId());
//        System.out.println("**********************************");
//        System.out.println(voteService.getTodayVotingResult());
    }

    private static <T> void printItems(List<T> list) {
        list.forEach(System.out::println);
    }
}
