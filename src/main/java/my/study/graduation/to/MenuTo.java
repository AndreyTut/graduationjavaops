package my.study.graduation.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

public class MenuTo {
    private String restaurant;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int restaurant_id;
    @NotNull
    private Map<String, String> dishes;
    private int id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate date;

    public MenuTo(int id, String restaurant, int restaurant_id, Map<String, String> dishes, LocalDate date) {
        this.restaurant = restaurant;
        this.restaurant_id = restaurant_id;
        this.dishes = dishes;
        this.id = id;
        this.date = date;
    }

    public MenuTo() {
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, String> getDishes() {
        return dishes;
    }

    public void setDishes(Map<String, String> dishes) {
        this.dishes = dishes;
    }
}
