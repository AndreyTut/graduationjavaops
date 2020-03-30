package my.study.graduation.util;

import my.study.graduation.model.Menu;
import my.study.graduation.to.MenuTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToConverters {

    private static MenuTo menuIntoMenuTo(Menu menu) {
        Map<String, Double> dishMap = new HashMap<>();
        menu.getDishes().forEach(dish -> dishMap.put(dish.getName(), dish.getPrice() / 100.0));
        return new MenuTo(menu.getId(), menu.getRestaurant().getName(), dishMap);
    }

    public static List<MenuTo> menuListIntoMenuToList(List<Menu> menus) {
        return menus.stream()
                .map(ToConverters::menuIntoMenuTo)
                .collect(Collectors.toList());
    }
}
