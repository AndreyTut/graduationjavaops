package my.study.graduation.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {

    private User user;
    private Menu menu;
    private LocalDateTime voutingTime;

    public Vote(Integer id, User user, Menu menu, LocalDateTime voutingTime) {
        super(id);
        this.user = user;
        this.menu = menu;
        this.voutingTime = voutingTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public LocalDateTime getVoutingTime() {
        return voutingTime;
    }

    public void setVoutingTime(LocalDateTime voutingTime) {
        this.voutingTime = voutingTime;
    }
}
