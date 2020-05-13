package my.study.graduation.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "day")
    private LocalDate day;

    public Menu(Restaurant restaurant, List<Dish> dishes, LocalDate day) {
        this(null, restaurant, dishes, day);
    }

    public Menu(Integer id, Restaurant restaurant, List<Dish> dishes, LocalDate day) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.day = day;
        dishes.forEach(dish -> dish.setMenu(this));
    }

    public Menu() {
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
        dishes.forEach(dish -> dish.setMenu(this));
        this.dishes = dishes;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}
