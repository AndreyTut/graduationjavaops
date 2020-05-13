package my.study.graduation.service;

import my.study.graduation.model.*;
import my.study.graduation.to.MenuTo;
import my.study.graduation.to.RestaurantWithVoices;
import my.study.graduation.util.ToConverters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class TestData {

    public static final int START_SEQ = 100000;

    public static final User USER = new User(START_SEQ, "User", "user@yandex.ru", "{noop}password", EnumSet.of(Role.ROLE_USER), true);
    public static final User USER_UPD = new User(START_SEQ, "User", "user@yandex.ru", "changed passw", EnumSet.of(Role.ROLE_USER), true);
    public static final User ADMIN = new User(START_SEQ + 1, "Admin", "admin@gmail.com", "{noop}admin", EnumSet.of(Role.ROLE_ADMIN), true);


    public static final Restaurant RESTAURANT_1 = new Restaurant(START_SEQ + 2, "Restor_1");
    public static final Restaurant RESTAURANT_1_UPD = new Restaurant(START_SEQ + 2, "Restor_upd");
    public static final Restaurant RESTAURANT_2 = new Restaurant(START_SEQ + 3, "Restor_2");
    public static final Restaurant RESTAURANT_3 = new Restaurant(START_SEQ + 4, "Restor_3");

    public static Menu MENU = new Menu(START_SEQ + 5, RESTAURANT_2, Arrays.asList(new Dish(100009, "dish1", 1231))
            , LocalDate.of(2015, 5, 30));
    public static Menu MENU1 = new Menu(START_SEQ + 6, RESTAURANT_3, Arrays.asList(new Dish(100010, "dish2", 1231))
            , LocalDate.of(2015, 5, 30));
    public static Menu MENU_NEW = new Menu(RESTAURANT_2, Arrays.asList(new Dish("dish", 1231)), LocalDate.now());
    public static Menu MENU_UPD = new Menu(START_SEQ + 5, RESTAURANT_1, Arrays.asList(new Dish(100009, "dish1", 1231))
            , LocalDate.of(2015, 5, 30));

    public static final MenuTo MENU_TO_1 = ToConverters.menuIntoMenuTo(MENU);
    public static final MenuTo MENU_TO_2 = ToConverters.menuIntoMenuTo(MENU1);

    public static final List<RestaurantWithVoices> voteResults =
            Arrays.asList(new RestaurantWithVoices(RESTAURANT_2.getName(), 2),
                    new RestaurantWithVoices(RESTAURANT_3.getName(), 0));
}
