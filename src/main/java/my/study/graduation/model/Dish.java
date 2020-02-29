package my.study.graduation.model;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public Dish(int id, String name, int price) {
        super(id, name);
        this.name = name;
        this.price = price;
    }

    public Dish(String name, int price) {
        super(null, name);
        this.name = name;
        this.price = price;
    }

    public Dish() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
