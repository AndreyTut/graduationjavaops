package my.study.graduation.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {


    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "date")
    private LocalDate votingDate;


    public Vote(Integer id, int userId, int menuId, LocalDate votingDate) {
        super(id);
        this.userId = userId;
        this.menuId = menuId;
        this.votingDate = votingDate;
    }

    public Vote(Integer userId, Integer menuId, LocalDate votingDate) {
        this(null, userId, menuId, votingDate);
    }

    public Vote() {
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public LocalDate getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(LocalDate votingDate) {
        this.votingDate = votingDate;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", menuId=" + menuId +
                ", votingDate=" + votingDate +
                '}';
    }
}
