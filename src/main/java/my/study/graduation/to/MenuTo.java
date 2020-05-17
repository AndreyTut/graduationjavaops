package my.study.graduation.to;

import java.util.Map;

public class MenuTo {
    private String restaurant;
    private Map<String, Double> dishes;
    private int id;

    public MenuTo(int id, String restaurant, Map<String, Double> dishes) {
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.id = id;
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

    public Map<String, Double> getDishes() {
        return dishes;
    }

    public void setDishes(Map<String, Double> dishes) {
        this.dishes = dishes;
    }


//
//    @Override
//    public String toString() {
//        return "MenuTo{" +
//                "restaurant=" + restaurant +
//                ", dishes=" + dishes +
//                ", id=" + id +
//                '}';
//    }
}
