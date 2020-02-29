import javassist.NotFoundException;
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
        service.save(new User("Dmytro", "emael@com.com", "efefefc", EnumSet.of(Role.ROLE_ADMIN), true));
        printItems(service.getAll());
        CrudMenuRepository repository = context.getBean(CrudMenuRepository.class);
        MenuService menuService = context.getBean(MenuService.class);
        printItems(menuService.getForDay(LocalDate.of(2015, 5, 30)));
        System.out.println("*************************************************************************");
        Menu menu = repository.getById(100005).orElseThrow(() -> new NotFoundInDataBaseException(Menu.class, 10000));
        System.out.println(menu);
        //menu.setId(100030);
        RestaurantService restaurantService = context.getBean(RestaurantService.class);
        Restaurant cityHall = new Restaurant("CityHall");
        restaurantService.save(cityHall);
        menu.setRestaurant(cityHall);
        menu.setDishes(Arrays.asList(new Dish("banana", 100), new Dish("potato", 100)));
        menu.setDay(LocalDate.now());
        repository.save(menu);
        Menu menuNew = new Menu(null, restaurantService.save(new Restaurant("Mexico")),
                Arrays.asList(new Dish("bananas", 200), new Dish("potatos", 300)),
                LocalDate.now());
        menuService.save(menuNew);
        System.out.println("*************************************************************************");
        printItems(menuService.getForDay(menuNew.getDay()));
        printItems(menuService.getForToDay());
        System.out.println(menuNew);
    }

    private static <T> void printItems(List<T> list) {
        list.forEach(System.out::println);
    }
}
