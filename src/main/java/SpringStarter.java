import javassist.NotFoundException;
import my.study.graduation.model.Role;
import my.study.graduation.model.User;
import my.study.graduation.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class SpringStarter {
    public static void main(String[] args) throws NotFoundException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-db.xml", "spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
        UserService service = context.getBean(UserService.class);
        printItems(service.getAll());
        service.save(new User("caco", "qwe@sdf.com", "psww", EnumSet.of(Role.ROLE_ADMIN), true));
        printItems(service.getAll());
        User x = service.get(100000);
        User y = service.get(100000);
        x.setEmail("qwertyfgd");
        x.setPassword("q");
        x.setId(123);
        x = service.save(x);
        printItems(service.getAll());

//        RestaurantService restaurantService = context.getBean(RestaurantService.class);
//        printItems(restaurantService.getAll());
//        Restaurant upd = new Restaurant(123456, "tisa");
//        printItems(restaurantService.getAll());
//        System.out.println("********************************************************************");
//        System.out.println(upd);
//        System.out.println("***********************************");
//        restaurantService.delete(upd);
//        printItems(restaurantService.getAll());
        //  service.delete(x);
        //       System.out.println(service.get(124));
        service.delete(x);
        printItems(service.getAll());

    }

    private static <T> void printItems(List<T> list) {
        list.forEach(System.out::println);
    }
}
