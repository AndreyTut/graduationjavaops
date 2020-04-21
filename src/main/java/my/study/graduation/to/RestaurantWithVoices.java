package my.study.graduation.to;

public class RestaurantWithVoices {
    private String name;
    private int voises;

    public RestaurantWithVoices(String name, int voises) {
        this.name = name;
        this.voises = voises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVoises() {
        return voises;
    }

    public void setVoises(int voises) {
        this.voises = voises;
    }
}
