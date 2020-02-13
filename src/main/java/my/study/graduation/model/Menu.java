package my.study.graduation.model;

import java.time.LocalDate;
import java.util.List;

public class Menu extends AbstractBaseEntity {

    private Restaurant restaurant;
    private List<Dish> dishes;
    private LocalDate day;

    public Menu(Integer id, Restaurant restaurant, List<Dish> dishes, LocalDate day) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.day = day;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}
