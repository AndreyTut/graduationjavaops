package my.study.graduation.model;

public class Dish extends AbstractNamedEntity {

    private Money price;

    public Dish(int id, String name, Money price) {
        super(id, name);
        this.name = name;
        this.price = price;
    }

    public Dish(String name, Money price) {
        super(null, name);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
