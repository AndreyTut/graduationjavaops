package my.study.graduation.model;

import java.time.LocalDate;

public class Vote extends AbstractBaseEntity {

    private User user;
    private Menu menu;
    private LocalDate votingDate;

    public Vote(Integer id, User user, Menu menu, LocalDate votingDate) {
        super(id);
        this.user = user;
        this.menu = menu;
        this.votingDate = votingDate;
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

    public LocalDate getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(LocalDate votingDate) {
        this.votingDate = votingDate;
    }
}
