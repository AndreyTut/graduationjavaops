package my.study.graduation.util;

import my.study.graduation.model.Dish;
import my.study.graduation.model.Menu;
import my.study.graduation.model.Role;
import my.study.graduation.model.User;
import my.study.graduation.to.MenuTo;
import my.study.graduation.to.UserTo;

import java.util.*;
import java.util.stream.Collectors;

public class ToConverters {


    public static MenuTo menuIntoMenuTo(Menu menu) {
        Map<String, String> dishMap = new HashMap<>();
        menu.getDishes().forEach(dish -> dishMap.put(dish.getName(), String.format("%.2f $", dish.getPrice() / 100.0)));
        return new MenuTo(menu.getId(), menu.getRestaurant().getName(), menu.getRestaurant().getId(), dishMap, menu.getDay());
    }

    public static Menu menuToIntoMenu(MenuTo menuTo) {
        List<Dish> dishes = dishesFromTo(menuTo.getDishes());
        return new Menu(null, dishes, menuTo.getDate());
    }

    public static List<Dish> dishesFromTo(Map<String, String> map) {
        List<Dish> dishes = new ArrayList<>();
        map.forEach((key, value) -> dishes.add(new Dish(key, Integer.parseInt(value))));
        return dishes;
    }

    public static List<MenuTo> menuListIntoMenuToList(List<Menu> menus) {
        return menus.stream()
                .distinct()
                .map(ToConverters::menuIntoMenuTo)
                .collect(Collectors.toList());
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(userTo.getName(), userTo.getEmail(), userTo.getPassword(), EnumSet.of(Role.ROLE_USER), true);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

}
