package my.study.graduation.model;

import java.time.LocalDate;
import java.util.List;

public class Menu extends AbstractBaseEntity {

    private Restoraunt restoraunt;
    private List<Dish> dishes;
    private LocalDate day;

    public Menu(Integer id, Restoraunt restoraunt, List<Dish> dishes, LocalDate day) {
        super(id);
        this.restoraunt = restoraunt;
        this.dishes = dishes;
        this.day = day;
    }

    public Restoraunt getRestoraunt() {
        return restoraunt;
    }

    public void setRestoraunt(Restoraunt restoraunt) {
        this.restoraunt = restoraunt;
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
